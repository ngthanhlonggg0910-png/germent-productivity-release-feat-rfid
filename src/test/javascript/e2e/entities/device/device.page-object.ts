import { element, by, ElementFinder } from 'protractor';

export class DeviceComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-device div table .btn-danger'));
  title = element.all(by.css('jhi-device div h2#page-heading span')).first();

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

export class DeviceUpdatePage {
  pageTitle = element(by.id('jhi-device-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  nameInput = element(by.id('field_name'));
  devIdsInput = element(by.id('field_devIds'));
  stateTopicInput = element(by.id('field_stateTopic'));
  lwtTopicInput = element(by.id('field_lwtTopic'));
  swInput = element(by.id('field_sw'));
  moduleInput = element(by.id('field_module'));
  restartReasonInput = element(by.id('field_restartReason'));
  timeInput = element(by.id('field_time'));
  uptimeInput = element(by.id('field_uptime'));
  hostNameInput = element(by.id('field_hostName'));
  ipAddressInput = element(by.id('field_ipAddress'));
  lwtSelect = element(by.id('field_lwt'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
  }

  async setDevIdsInput(devIds) {
    await this.devIdsInput.sendKeys(devIds);
  }

  async getDevIdsInput() {
    return await this.devIdsInput.getAttribute('value');
  }

  async setStateTopicInput(stateTopic) {
    await this.stateTopicInput.sendKeys(stateTopic);
  }

  async getStateTopicInput() {
    return await this.stateTopicInput.getAttribute('value');
  }

  async setLwtTopicInput(lwtTopic) {
    await this.lwtTopicInput.sendKeys(lwtTopic);
  }

  async getLwtTopicInput() {
    return await this.lwtTopicInput.getAttribute('value');
  }

  async setSwInput(sw) {
    await this.swInput.sendKeys(sw);
  }

  async getSwInput() {
    return await this.swInput.getAttribute('value');
  }

  async setModuleInput(module) {
    await this.moduleInput.sendKeys(module);
  }

  async getModuleInput() {
    return await this.moduleInput.getAttribute('value');
  }

  async setRestartReasonInput(restartReason) {
    await this.restartReasonInput.sendKeys(restartReason);
  }

  async getRestartReasonInput() {
    return await this.restartReasonInput.getAttribute('value');
  }

  async setTimeInput(time) {
    await this.timeInput.sendKeys(time);
  }

  async getTimeInput() {
    return await this.timeInput.getAttribute('value');
  }

  async setUptimeInput(uptime) {
    await this.uptimeInput.sendKeys(uptime);
  }

  async getUptimeInput() {
    return await this.uptimeInput.getAttribute('value');
  }

  async setHostNameInput(hostName) {
    await this.hostNameInput.sendKeys(hostName);
  }

  async getHostNameInput() {
    return await this.hostNameInput.getAttribute('value');
  }

  async setIpAddressInput(ipAddress) {
    await this.ipAddressInput.sendKeys(ipAddress);
  }

  async getIpAddressInput() {
    return await this.ipAddressInput.getAttribute('value');
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

export class DeviceDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-device-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-device'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
