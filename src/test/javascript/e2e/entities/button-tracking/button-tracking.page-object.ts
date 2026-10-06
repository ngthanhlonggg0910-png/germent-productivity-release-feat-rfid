import { element, by, ElementFinder } from 'protractor';

export class ButtonTrackingComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-button-tracking div table .btn-danger'));
  title = element.all(by.css('jhi-button-tracking div h2#page-heading span')).first();

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

export class ButtonTrackingUpdatePage {
  pageTitle = element(by.id('jhi-button-tracking-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  updateDateInput = element(by.id('field_updateDate'));
  currentAliasStateInput = element(by.id('field_currentAliasState'));
  trackingIdInput = element(by.id('field_trackingId'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setUpdateDateInput(updateDate) {
    await this.updateDateInput.sendKeys(updateDate);
  }

  async getUpdateDateInput() {
    return await this.updateDateInput.getAttribute('value');
  }

  async setCurrentAliasStateInput(currentAliasState) {
    await this.currentAliasStateInput.sendKeys(currentAliasState);
  }

  async getCurrentAliasStateInput() {
    return await this.currentAliasStateInput.getAttribute('value');
  }

  async setTrackingIdInput(trackingId) {
    await this.trackingIdInput.sendKeys(trackingId);
  }

  async getTrackingIdInput() {
    return await this.trackingIdInput.getAttribute('value');
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

export class ButtonTrackingDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-buttonTracking-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-buttonTracking'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
