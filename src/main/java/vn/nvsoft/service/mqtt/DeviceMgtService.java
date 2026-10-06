package vn.nvsoft.service.mqtt;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vn.nvsoft.domain.ButtonTracking;
import vn.nvsoft.domain.ChuyenTramSound;
import vn.nvsoft.domain.MapControlButton;
import vn.nvsoft.domain.NVButton;
import vn.nvsoft.domain.NVButtonTrackingType;
import vn.nvsoft.domain.enumeration.GroupButtonType;
import vn.nvsoft.domain.enumeration.LWT;
import vn.nvsoft.domain.enumeration.NVButtonState;
import vn.nvsoft.mqtt.model.Action;
import vn.nvsoft.mqtt.model.MessagePlayList;
import vn.nvsoft.mqtt.model.SensorHassState;
import vn.nvsoft.mqtt.model.SourceType;
import vn.nvsoft.mqtt.model.TeleInfo;
import vn.nvsoft.service.ButtonTrackingService;
import vn.nvsoft.service.DeviceService;
import vn.nvsoft.service.MapButtonActionService;
import vn.nvsoft.service.MapControlButtonService;
import vn.nvsoft.service.NVButtonService;
import vn.nvsoft.service.NVButtonTrackingTypeService;
import vn.nvsoft.service.TrackingService;
import vn.nvsoft.service.dto.DeviceDTO;
import vn.nvsoft.service.dto.MapButtonActionDTO;
import vn.nvsoft.service.dto.NVButtonDTO;
import vn.nvsoft.service.dto.NVButtonTrackingTypeDTO;
import vn.nvsoft.service.dto.TrackingDTO;
import vn.nvsoft.service.impl.ChuyenTramSoundServiceImpl;
import vn.nvsoft.service.mapper.JsonMapper;

@Service
public class DeviceMgtService {

    private final Logger log = LoggerFactory.getLogger(DeviceMgtService.class.getName());

    private static final String CHUYEN_AUDIO = "chuyen/";
    private static final String TRAM_AUDIO = "tram/";
    private static final String CAN_KY_THUAT_AUDIO = "cankythuat/L%dS%dKT.mp3";
    private static final String CAN_CO_DIEN_AUDIO = "cancodien/L%dS%dCD.mp3";
    private static final String CAN_TO_TRUONG_AUDIO = "cantotruong/L%dS%dTT.mp3";

    private final NVButtonService m_nvButtonService;
    private final DeviceService m_deviceService;
    private final TrackingService m_trackingService;
    private final ButtonTrackingService m_buttonTrackingService;
    private final NVButtonTrackingTypeService m_nvButtonTrackingTypeService;
    private final MapControlButtonService m_mapControlButtonService;
    private final MapButtonActionService m_mapButtonActionService;
    private final ChuyenTramSoundServiceImpl m_chuyenTramSoundService;
    private MqttV5ConnectionMgt m_mqttV5ConnectionMgt;

    private Object m_syncCreateDevice = new Object();

    public DeviceMgtService(NVButtonService nvButtonService, DeviceService deviceService, TrackingService trackingService, ButtonTrackingService buttonTrackingService,
        NVButtonTrackingTypeService nvButtonTrackingTypeService, MapControlButtonService mapControlButtonService, MapButtonActionService mapButtonActionService,
        ChuyenTramSoundServiceImpl chuyenTramSoundService) {
        m_nvButtonService = nvButtonService;
        m_deviceService = deviceService;
        m_trackingService = trackingService;
        m_buttonTrackingService = buttonTrackingService;
        m_nvButtonTrackingTypeService = nvButtonTrackingTypeService;
        m_mapControlButtonService = mapControlButtonService;
        m_mapButtonActionService = mapButtonActionService;
        m_chuyenTramSoundService = chuyenTramSoundService;
    }

    public void setMqttV5ConnectionMgt(MqttV5ConnectionMgt mqttV5ConnectionMgt) {
        m_mqttV5ConnectionMgt = mqttV5ConnectionMgt;
    }

    public void discoveredSwitch(NVButtonDTO buttonModel) {
        List<NVButtonDTO> existButtons = m_nvButtonService.findByCmdTopics(buttonModel.getCmndTopic());
        if (existButtons != null && !existButtons.isEmpty()) {
            log.debug("The button existed: {}, so ignore it", existButtons.toString());
        } else {
            synchronized (m_syncCreateDevice) {
                existButtons = m_nvButtonService.findByCmdTopics(buttonModel.getCmndTopic());
                if (existButtons == null || existButtons.isEmpty()) {
                    updateButtonTrackingType(buttonModel);
                    Optional<DeviceDTO> device = m_deviceService.findOneByLwtTopic(buttonModel.getAvtyTopic());
                    if (device.isPresent()) {
                        log.debug("The device existed {}, so update button to that device", device.get());
                        buttonModel.setDeviceId(device.get().getId());
                        m_nvButtonService.save(buttonModel);
                    } else {
                        DeviceDTO deviceDTO = new DeviceDTO();
                        deviceDTO.setDevIds(buttonModel.getDevIds());
                        deviceDTO.setLwtTopic(buttonModel.getAvtyTopic());
                        deviceDTO.setStateTopic(buttonModel.getTeleTopic());
                        if (!Strings.isNullOrEmpty(buttonModel.getDeviceName())) {
                            deviceDTO.setName(buttonModel.getDeviceName());
                        }
                        log.debug("Create new device {}", deviceDTO.toString());
                        deviceDTO = m_deviceService.save(deviceDTO);
                        buttonModel.setDeviceId(deviceDTO.getId());
                        m_nvButtonService.save(buttonModel);
                    }
                }
            }
        }
    }

    /**
     * Auto mapp ButtongTrackingType
     * @param buttonModel
     */
    private void updateButtonTrackingType(NVButtonDTO buttonModel) {
        List<NVButtonTrackingTypeDTO> buttonTrackingTypes = m_nvButtonTrackingTypeService.findAll();
        if (!buttonTrackingTypes.isEmpty()) {
            for (NVButtonTrackingTypeDTO buttonTrackingType : buttonTrackingTypes) {
                if (buttonModel.getCmndTopic() != null
                    && (buttonModel.getCmndTopic().endsWith(buttonTrackingType.getId() + "") || buttonModel.getCmndTopic().endsWith(buttonTrackingType.getId() + "T"))) {
                    buttonModel.setTrackingTypeId(buttonTrackingType.getId());
                    break;
                }
            }
        }
    }

    /**
     * This listener handle message on topic nvsoft/#/stat/POWERx
     * The payload message is a string not json object
     * Only update to database if the state different with state in db
     * Need to update NV_Button_Tracking base on NV_Button_Tracking_Type
     * @param topic
     * @param pl
     */
    public void updateButtonState(String topic, String pl) {
        Optional<NVButton> nvButtonOpt = m_nvButtonService.findOneByStatTopic(topic);
        if (nvButtonOpt.isPresent()) {
            NVButton nvButton = nvButtonOpt.get();
            if (pl.equalsIgnoreCase(nvButton.getPlOn())) {
                if (nvButton.getState() == null || NVButtonState.OFF == nvButton.getState()) {
                    updateButtonStateAndTracking(nvButton, NVButtonState.OFF, NVButtonState.ON);
                }
            } else if (pl.equalsIgnoreCase(nvButton.getPlOff())) {
                if (nvButton.getState() == null || NVButtonState.ON == nvButton.getState()) {
                    updateButtonStateAndTracking(nvButton, NVButtonState.ON, NVButtonState.OFF);
                }
            }
        } else {
            log.warn("Can not find the button for topic {}", topic);
        }
    }

    private void updateButtonStateAndTracking(NVButton nvButton, NVButtonState fromState, NVButtonState toState) {
        nvButton.setLastUpdateState(Instant.now());
        nvButton.setState(toState);
        nvButton.setLwt(LWT.ON_LINE);
        //update mapping state
        if (nvButton.getTrackingType() != null && GroupButtonType.LIGHT != nvButton.getGroupType()) {
            ButtonTracking buttonTracking = updateButtonTracking(nvButton);
            updateTracking(nvButton, buttonTracking);
            controlTargetButton(nvButton, buttonTracking);
        }
        m_nvButtonService.save(nvButton);
    }

    private void controlTargetButton(NVButton sourceButton, ButtonTracking sourceButtonTracking) {
        List<MapControlButton> mapControlButtons = m_mapControlButtonService.findAllBySourceButton(sourceButton);
        if (!mapControlButtons.isEmpty()) {
            for (MapControlButton mapControlButton : mapControlButtons) {
                NVButtonState targetState = getTargetButtonState(sourceButton, sourceButtonTracking);
                if (targetState != null) {
                    checkAndUpdateTargetButtonToState(sourceButton, mapControlButton.getTargetButton(), targetState);
                }
            }

        } else {
            log.warn("Can not find map control button for source button {} type {}", sourceButton.getCmndTopic(), sourceButton.getTrackingType().getName());
        }
    }

    private void checkAndUpdateTargetButtonToState(NVButton sourceButton, NVButton targetButton, NVButtonState targetState) {
        if (targetState == NVButtonState.ON) {
            updateTargetButtonToState(targetButton, targetState);
        } else {
            List<MapControlButton> mapControlButtons = m_mapControlButtonService.findAllByTargetButton(targetButton);
            if (mapControlButtons.size() > 1) {
                boolean isOtherSourceBtTurnOn = false;
                for (MapControlButton mapControlButton : mapControlButtons) {
                    if (!mapControlButton.getSourceButton().getId().equals(sourceButton.getId())) {
                        if (isTurnOnTargetButton(mapControlButton.getSourceButton(), mapControlButton.getTargetButton())) {
                            isOtherSourceBtTurnOn = true;
                            break;
                        }
                    }
                }
                if (!isOtherSourceBtTurnOn) {
                    updateTargetButtonToState(targetButton, targetState);
                }
            } else {
                updateTargetButtonToState(targetButton, targetState);
            }
        }
    }

    private void updateTargetButtonToState(NVButton targetButton, NVButtonState targetState) {
        String payload = getTargetPayloadValue(targetButton, targetState);
        String cmndTopic = targetButton.getCmndTopic();
        m_mqttV5ConnectionMgt.publishMessage(payload, cmndTopic);
    }

    /**
     * Check sourceButton alias state is turn on target or turn off target
     * @param sourceButton
     * @param targetButton
     * @return
     */
    private boolean isTurnOnTargetButton(NVButton sourceButton, NVButton targetButton) {
        NVButtonState targetState = getTargetButtonState(sourceButton, sourceButton.getButtonTracking());
        if (targetButton != null) {
            return NVButtonState.ON == targetState;
        }
        return false;
    }

    private String getTargetPayloadValue(NVButton targetButton, NVButtonState targetState) {
        switch (targetState) {
            case ON:
                return targetButton.getPlOn();
            case OFF:
                return targetButton.getPlOff();
        }
        return targetButton.getPlOff();
    }

    private NVButtonState getTargetButtonState(NVButton sourceButton, ButtonTracking sourceButtonTracking) {
        if (sourceButtonTracking == null) {
            log.warn("Can not found button tracking for button {}", sourceButton.getCmndTopic());
            return null;
        }
        List<MapButtonActionDTO> mapActions = m_mapButtonActionService
            .findBySourceAliasStateAndTrackingType(sourceButtonTracking.getCurrentAliasState(), sourceButton.getTrackingType());
        if (!mapActions.isEmpty()) {
            //handle only one mapping
            return mapActions.get(0).getTargetState();
        } else {
            log.warn("Can not find MapButtonAction for source button topic {} with source alias state {} and tracking type {} ", sourceButton.getCmndTopic(),
                sourceButtonTracking.getCurrentAliasState(), sourceButton.getTrackingType().getName());
        }
        return null;
    }

    /**
     * Update alias state
     * @param nvButton
     * @return
     */
    private ButtonTracking updateButtonTracking(NVButton nvButton) {
        ButtonTracking buttonTracking = nvButton.getButtonTracking();
        if (buttonTracking == null) {
            buttonTracking = new ButtonTracking();
            nvButton.setButtonTracking(buttonTracking);
        }
        updateButtonTrackingAliasState(buttonTracking, nvButton.getTrackingType());
        buttonTracking.setNVButton(nvButton);
        return m_buttonTrackingService.save(buttonTracking);
    }

    private void buildAndSendAudioMsg(NVButton nvButton, Action action) {
        try {
            Optional<ChuyenTramSound> chuyenTramOp = m_chuyenTramSoundService.findChuyenTramSoundByButton(nvButton);
            if (chuyenTramOp.isPresent()) {
                ChuyenTramSound chuyenTram = chuyenTramOp.get();
                MessagePlayList messagePlayList = new MessagePlayList();
                messagePlayList.setAction(action);
                messagePlayList.setPlayLists(buildAudioPlaylists(chuyenTram, nvButton));
                messagePlayList.setSourceType(SourceType.LOCAL);
                messagePlayList.setReceivedOnTopic(chuyenTram.getSoundPlayerTopic());
                messagePlayList.setButtonId(nvButton.getId());
                messagePlayList.setButtonName(nvButton.getName());
                String msg = JsonMapper.convertToJsonString(messagePlayList);
                m_mqttV5ConnectionMgt.publishMessage(msg, messagePlayList.getReceivedOnTopic());
            } else {
                log.debug("Khong tim thay chuyen tram cho {}", nvButton.getLwt());
            }
        } catch (Exception e) {
            log.warn("Lay chuyen tram error ", e);
        }
    }

    private List<String> buildAudioPlaylists(ChuyenTramSound chuyenTram, NVButton nvButton) {
        List<String> playLists = Lists.newArrayList();
        if (nvButton.getTrackingType().getId().equals(1l)) {
            playLists.add(String.format(CAN_CO_DIEN_AUDIO, chuyenTram.getMaChuyen(), chuyenTram.getSTTTram()));
        } else if (nvButton.getTrackingType().getId().equals(2l)) {
            playLists.add(String.format(CAN_TO_TRUONG_AUDIO, chuyenTram.getMaChuyen(), chuyenTram.getSTTTram()));
        } else {
            playLists.add(String.format(CAN_KY_THUAT_AUDIO, chuyenTram.getMaChuyen(), chuyenTram.getSTTTram()));
        }
        return playLists;
    }

    private void triggerPlayAudio(NVButton nvButton) {

        buildAndSendAudioMsg(nvButton, Action.PLAY);
    }

    private void triggerStopAudio(NVButton nvButton) {
        buildAndSendAudioMsg(nvButton, Action.STOP);
    }

    private void updateTracking(NVButton nvButton, ButtonTracking buttonTracking) {
        TrackingDTO tracking = null;
        boolean isNewTracking = false;
        if (buttonTracking.getTrackingId() == null || buttonTracking.getCurrentAliasState() == 1) {
            tracking = new TrackingDTO();
            isNewTracking = true;
            triggerPlayAudio(nvButton);
        } else {
            triggerStopAudio(nvButton);
            Optional<TrackingDTO> trackingOpt = m_trackingService.findOne(buttonTracking.getTrackingId());
            if (trackingOpt.isPresent()) {
                tracking = trackingOpt.get();
            } else {
                log.warn("Can not find Tracking for id {}, so create new tracking", buttonTracking.getTrackingId());
                tracking = new TrackingDTO();
                isNewTracking = true;
                buttonTracking.setCurrentAliasState(1);
                triggerPlayAudio(nvButton);
            }
        }
        updateStateTrackingTime(tracking, buttonTracking);
        tracking.setNvButtonId(nvButton.getId());
        tracking.setTrackingTypeId(nvButton.getTrackingType().getId());
        tracking = m_trackingService.save(tracking);
        if (buttonTracking.getTrackingId() == null || isNewTracking) {
            buttonTracking.setTrackingId(tracking.getId());
            m_buttonTrackingService.save(buttonTracking);
        }
    
    }

    private void updateStateTrackingTime(TrackingDTO tracking, ButtonTracking buttonTracking) {
        switch (buttonTracking.getCurrentAliasState()) {
            case 1:
                tracking.setState1Time(Instant.now());
                break;
            case 2:
                tracking.setState2Time(Instant.now());
                break;
            case 3:
                tracking.setState3Time(Instant.now());
                break;
            case 4:
                tracking.setState4Time(Instant.now());
                break;
            case 5:
                tracking.setState5Time(Instant.now());
                break;
            case 6:
                tracking.setState6Time(Instant.now());
                break;
            default:
                log.warn("Does not support tracking for state {}", buttonTracking.getCurrentAliasState());
                break;
        }
    }

    private void updateButtonTrackingAliasState(ButtonTracking buttonTracking, NVButtonTrackingType trackingType) {
        if (buttonTracking.getCurrentAliasState() == null) {
            buttonTracking.setCurrentAliasState(1);
        } else {
            Integer nextState = buttonTracking.getCurrentAliasState() + 1;
            if (nextState > trackingType.getNumberOfState()) {
                nextState = 1;
            }
            buttonTracking.setCurrentAliasState(nextState);
        }
        buttonTracking.setUpdateDate(Instant.now());
    }

    /**
     * Update Device and button avail state Online or Offline
     * @param topic format nvsoft/#/tele/LWT
     * @param state
     */
    public void updateDeviceAndButtonAvailState(String topic, String state) {
        updateButtonAvailState(topic, state);
    }

    private void updateButtonAvailState(String topic, String state) {
        List<NVButton> nvButtons = m_nvButtonService.findByAvtyTopic(topic);
        LWT lwt = LWT.UN_KNOW;
        for (NVButton nvButton : nvButtons) {
            lwt = getLwt(nvButton, state);
            nvButton.setLwt(lwt);
            m_nvButtonService.save(nvButton);
        }
        Optional<DeviceDTO> device = m_deviceService.findOneByLwtTopic(topic);
        if (device.isPresent()) {
            DeviceDTO deviceDTO = device.get();
            deviceDTO.setLwt(lwt);
            m_deviceService.save(deviceDTO);
        }
    }

    private LWT getLwt(NVButton nvButton, String state) {
        if (nvButton.getPlAvail().equalsIgnoreCase(state)) {
            return LWT.ON_LINE;
        }
        if (nvButton.getPlNotAvail().equalsIgnoreCase(state)) {
            return LWT.OFF_LINE;
        }
        return LWT.UN_KNOW;
    }

    /**
     * Update device details like ip address
     * @param lwtTopic
     * @param buttonModel
     */
    public void updateDeviceDetails(String lwtTopic, SensorHassState buttonModel) {
        Optional<DeviceDTO> deviceOpt = m_deviceService.findOneByLwtTopic(lwtTopic);
        if (deviceOpt.isPresent()) {
            DeviceDTO device = deviceOpt.get();
            device.setSw(buttonModel.getVersion());
            device.setHostName(buttonModel.getHostname());
            device.setIpAddress(buttonModel.getiPAddress());
            device.setRestartReason(buttonModel.getRestartReason());
            device.setUptime(buttonModel.getUptime());
            device.setModule(buttonModel.getModuleOrTemplate());
            device.setName(buttonModel.getHostname());
            m_deviceService.save(device);
        } else {
            log.warn("Can not found device with lwt topic {} to update sensor hass state {}", lwtTopic, buttonModel.toString());
        }
    }

    /**
     * nvsoft/sewing/tasmota_756F7C/stat/BUTTON3T = {"TRIG":"SINGLE"}
     * The user press button
     * @param topic
     * @param trim
     */
    public void buttonTrigger(String topic, String trim) {
        Optional<NVButton> nvButtonOpt = m_nvButtonService.findOneByStatTopic(topic);
        if (nvButtonOpt.isPresent()) {
            NVButton nvButton = nvButtonOpt.get();
            updateButtonStateAndTracking(nvButton, NVButtonState.OFF, NVButtonState.ON);
        } else {
            log.warn("Can not find the button for topic {}", topic);
        }
    }

    /**
     * Received msg from topic: nvsoft/sewing/nv57976_75CF4C/tele/INFO2 msg:
     *         {
     * 	"WebServerMode": "Admin",
     * 	"Hostname": "nv57976_75CF4C-3916",
     * 	"IPAddress": "192.168.1.4"
     * }
     * @param buttonModel
     */
    public void updateSewingDeviceInfo(TeleInfo buttonModel) {
        Optional<DeviceDTO> deviceOpt = m_deviceService.findOneByLwtTopic(buttonModel.getReceivedOnTopic());
        if (deviceOpt.isPresent()) {
            DeviceDTO device = deviceOpt.get();
            device.setHostName(buttonModel.getHostname());
            device.setIpAddress(buttonModel.getiPAddress());
            device.setName(buttonModel.getHostname());
            m_deviceService.save(device);
        } else {
            log.warn("Can not found device with lwt topic {} to update sensor hass state {}", buttonModel.getReceivedOnTopic(), buttonModel.toString());
        }
    }
}
