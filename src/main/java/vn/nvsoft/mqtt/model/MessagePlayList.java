package vn.nvsoft.mqtt.model;

import java.util.List;

public class MessagePlayList {
  private Action action;
  private List<String> playLists;
  private SourceType sourceType;
  private String receivedOnTopic;
  private Long buttonId;
  private String buttonName;

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public List<String> getPlayLists() {
        return playLists;
    }

    public void setPlayLists(List<String> playLists) {
        this.playLists = playLists;
    }

    public SourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(SourceType sourceType) {
        this.sourceType = sourceType;
    }

    public String getReceivedOnTopic() {
        return receivedOnTopic;
    }

    public void setReceivedOnTopic(String receivedOnTopic) {
        this.receivedOnTopic = receivedOnTopic;
    }

    public Long getButtonId() {
        return buttonId;
    }

    public void setButtonId(Long buttonId) {
        this.buttonId = buttonId;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj instanceof MessagePlayList) {
      MessagePlayList that = (MessagePlayList) obj;
      return buttonId.equals(that.buttonId);
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return buttonId.hashCode();
  }
}
