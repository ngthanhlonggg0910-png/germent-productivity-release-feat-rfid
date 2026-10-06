import { element, by, ElementFinder } from 'protractor';

export class NVButtonComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-nv-button div table .btn-danger'));
  title = element.all(by.css('jhi-nv-button div h2#page-heading span')).first();

  async clickOnCreateButton() {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton() {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons() {
    return this.deleteButtons.count();
  }

  async getTitle() {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class NVButtonUpdatePage {
  pageTitle = element(by.id('jhi-nv-button-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  nameInput = element(by.id('field_name'));
  discoveredOnTopicInput = element(by.id('field_discoveredOnTopic'));
  cmndTopicInput = element(by.id('field_cmndTopic'));
  plOffInput = element(by.id('field_plOff'));
  plOnInput = element(by.id('field_plOn'));
  uniqIdInput = element(by.id('field_uniqId'));
  devIdsInput = element(by.id('field_devIds'));
  teleTopicInput = element(by.id('field_teleTopic'));
  statTopicInput = element(by.id('field_statTopic'));
  avtyTopicInput = element(by.id('field_avtyTopic'));
  plAvailInput = element(by.id('field_plAvail'));
  plNotAvailInput = element(by.id('field_plNotAvail'));
  stateSelect = element(by.id('field_state'));
  lwtSelect = element(by.id('field_lwt'));
  lastUpdateStateInput = element(by.id('field_lastUpdateState'));
  groupTypeSelect = element(by.id('field_groupType'));
  buttonTrackingSelect = element(by.id('field_buttonTracking'));
  deviceSelect = element(by.id('field_device'));
  trackingTypeSelect = element(by.id('field_trackingType'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
  }

  async setDiscoveredOnTopicInput(discoveredOnTopic) {
    await this.discoveredOnTopicInput.sendKeys(discoveredOnTopic);
  }

  async getDiscoveredOnTopicInput() {
    return await this.discoveredOnTopicInput.getAttribute('value');
  }

  async setCmndTopicInput(cmndTopic) {
    await this.cmndTopicInput.sendKeys(cmndTopic);
  }

  async getCmndTopicInput() {
    return await this.cmndTopicInput.getAttribute('value');
  }

  async setPlOffInput(plOff) {
    await this.plOffInput.sendKeys(plOff);
  }

  async getPlOffInput() {
    return await this.plOffInput.getAttribute('value');
  }

  async setPlOnInput(plOn) {
    await this.plOnInput.sendKeys(plOn);
  }

  async getPlOnInput() {
    return await this.plOnInput.getAttribute('value');
  }

  async setUniqIdInput(uniqId) {
    await this.uniqIdInput.sendKeys(uniqId);
  }

  async getUniqIdInput() {
    return await this.uniqIdInput.getAttribute('value');
  }

  async setDevIdsInput(devIds) {
    await this.devIdsInput.sendKeys(devIds);
  }

  async getDevIdsInput() {
    return await this.devIdsInput.getAttribute('value');
  }

  async setTeleTopicInput(teleTopic) {
    await this.teleTopicInput.sendKeys(teleTopic);
  }

  async getTeleTopicInput() {
    return await this.teleTopicInput.getAttribute('value');
  }

  async setStatTopicInput(statTopic) {
    await this.statTopicInput.sendKeys(statTopic);
  }

  async getStatTopicInput() {
    return await this.statTopicInput.getAttribute('value');
  }

  async setAvtyTopicInput(avtyTopic) {
    await this.avtyTopicInput.sendKeys(avtyTopic);
  }

  async getAvtyTopicInput() {
    return await this.avtyTopicInput.getAttribute('value');
  }

  async setPlAvailInput(plAvail) {
    await this.plAvailInput.sendKeys(plAvail);
  }

  async getPlAvailInput() {
    return await this.plAvailInput.getAttribute('value');
  }

  async setPlNotAvailInput(plNotAvail) {
    await this.plNotAvailInput.sendKeys(plNotAvail);
  }

  async getPlNotAvailInput() {
    return await this.plNotAvailInput.getAttribute('value');
  }

  async setStateSelect(state) {
    await this.stateSelect.sendKeys(state);
  }

  async getStateSelect() {
    return await this.stateSelect.element(by.css('option:checked')).getText();
  }

  async stateSelectLastOption() {
    await this.stateSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setLwtSelect(lwt) {
    await this.lwtSelect.sendKeys(lwt);
  }

  async getLwtSelect() {
    return await this.lwtSelect.element(by.css('option:checked')).getText();
  }

  async lwtSelectLastOption() {
    await this.lwtSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setLastUpdateStateInput(lastUpdateState) {
    await this.lastUpdateStateInput.sendKeys(lastUpdateState);
  }

  async getLastUpdateStateInput() {
    return await this.lastUpdateStateInput.getAttribute('value');
  }

  async setGroupTypeSelect(groupType) {
    await this.groupTypeSelect.sendKeys(groupType);
  }

  async getGroupTypeSelect() {
    return await this.groupTypeSelect.element(by.css('option:checked')).getText();
  }

  async groupTypeSelectLastOption() {
    await this.groupTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async buttonTrackingSelectLastOption() {
    await this.buttonTrackingSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async buttonTrackingSelectOption(option) {
    await this.buttonTrackingSelect.sendKeys(option);
  }

  getButtonTrackingSelect(): ElementFinder {
    return this.buttonTrackingSelect;
  }

  async getButtonTrackingSelectedOption() {
    return await this.buttonTrackingSelect.element(by.css('option:checked')).getText();
  }

  async deviceSelectLastOption() {
    await this.deviceSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async deviceSelectOption(option) {
    await this.deviceSelect.sendKeys(option);
  }

  getDeviceSelect(): ElementFinder {
    return this.deviceSelect;
  }

  async getDeviceSelectedOption() {
    return await this.deviceSelect.element(by.css('option:checked')).getText();
  }

  async trackingTypeSelectLastOption() {
    await this.trackingTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async trackingTypeSelectOption(option) {
    await this.trackingTypeSelect.sendKeys(option);
  }

  getTrackingTypeSelect(): ElementFinder {
    return this.trackingTypeSelect;
  }

  async getTrackingTypeSelectedOption() {
    return await this.trackingTypeSelect.element(by.css('option:checked')).getText();
  }

  async save() {
    await this.saveButton.click();
  }

  async cancel() {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class NVButtonDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-nVButton-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-nVButton'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
