import { element, by, ElementFinder } from 'protractor';

export class MapButtonActionComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-map-button-action div table .btn-danger'));
  title = element.all(by.css('jhi-map-button-action div h2#page-heading span')).first();

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

export class MapButtonActionUpdatePage {
  pageTitle = element(by.id('jhi-map-button-action-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  sourceAliasStateInput = element(by.id('field_sourceAliasState'));
  targetStateSelect = element(by.id('field_targetState'));
  trackingTypeSelect = element(by.id('field_trackingType'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setSourceAliasStateInput(sourceAliasState) {
    await this.sourceAliasStateInput.sendKeys(sourceAliasState);
  }

  async getSourceAliasStateInput() {
    return await this.sourceAliasStateInput.getAttribute('value');
  }

  async setTargetStateSelect(targetState) {
    await this.targetStateSelect.sendKeys(targetState);
  }

  async getTargetStateSelect() {
    return await this.targetStateSelect.element(by.css('option:checked')).getText();
  }

  async targetStateSelectLastOption() {
    await this.targetStateSelect
      .all(by.tagName('option'))
      .last()
      .click();
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

export class MapButtonActionDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mapButtonAction-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mapButtonAction'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
