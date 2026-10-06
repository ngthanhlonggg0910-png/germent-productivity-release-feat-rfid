import { element, by, ElementFinder } from 'protractor';

export class TrackingComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-tracking div table .btn-danger'));
  title = element.all(by.css('jhi-tracking div h2#page-heading span')).first();

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

export class TrackingUpdatePage {
  pageTitle = element(by.id('jhi-tracking-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  nvButtonIdInput = element(by.id('field_nvButtonId'));
  state1TimeInput = element(by.id('field_state1Time'));
  state2TimeInput = element(by.id('field_state2Time'));
  state3TimeInput = element(by.id('field_state3Time'));
  state4TimeInput = element(by.id('field_state4Time'));
  state5TimeInput = element(by.id('field_state5Time'));
  state6TimeInput = element(by.id('field_state6Time'));
  trackingTypeSelect = element(by.id('field_trackingType'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNvButtonIdInput(nvButtonId) {
    await this.nvButtonIdInput.sendKeys(nvButtonId);
  }

  async getNvButtonIdInput() {
    return await this.nvButtonIdInput.getAttribute('value');
  }

  async setState1TimeInput(state1Time) {
    await this.state1TimeInput.sendKeys(state1Time);
  }

  async getState1TimeInput() {
    return await this.state1TimeInput.getAttribute('value');
  }

  async setState2TimeInput(state2Time) {
    await this.state2TimeInput.sendKeys(state2Time);
  }

  async getState2TimeInput() {
    return await this.state2TimeInput.getAttribute('value');
  }

  async setState3TimeInput(state3Time) {
    await this.state3TimeInput.sendKeys(state3Time);
  }

  async getState3TimeInput() {
    return await this.state3TimeInput.getAttribute('value');
  }

  async setState4TimeInput(state4Time) {
    await this.state4TimeInput.sendKeys(state4Time);
  }

  async getState4TimeInput() {
    return await this.state4TimeInput.getAttribute('value');
  }

  async setState5TimeInput(state5Time) {
    await this.state5TimeInput.sendKeys(state5Time);
  }

  async getState5TimeInput() {
    return await this.state5TimeInput.getAttribute('value');
  }

  async setState6TimeInput(state6Time) {
    await this.state6TimeInput.sendKeys(state6Time);
  }

  async getState6TimeInput() {
    return await this.state6TimeInput.getAttribute('value');
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

export class TrackingDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-tracking-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-tracking'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
