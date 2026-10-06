import { element, by, ElementFinder } from 'protractor';

export class MapControlButtonComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-map-control-button div table .btn-danger'));
  title = element.all(by.css('jhi-map-control-button div h2#page-heading span')).first();

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

export class MapControlButtonUpdatePage {
  pageTitle = element(by.id('jhi-map-control-button-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  sourceButtonSelect = element(by.id('field_sourceButton'));
  targetButtonSelect = element(by.id('field_targetButton'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async sourceButtonSelectLastOption() {
    await this.sourceButtonSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async sourceButtonSelectOption(option) {
    await this.sourceButtonSelect.sendKeys(option);
  }

  getSourceButtonSelect(): ElementFinder {
    return this.sourceButtonSelect;
  }

  async getSourceButtonSelectedOption() {
    return await this.sourceButtonSelect.element(by.css('option:checked')).getText();
  }

  async targetButtonSelectLastOption() {
    await this.targetButtonSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async targetButtonSelectOption(option) {
    await this.targetButtonSelect.sendKeys(option);
  }

  getTargetButtonSelect(): ElementFinder {
    return this.targetButtonSelect;
  }

  async getTargetButtonSelectedOption() {
    return await this.targetButtonSelect.element(by.css('option:checked')).getText();
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

export class MapControlButtonDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-mapControlButton-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-mapControlButton'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
