import { element, by, ElementFinder } from 'protractor';

export class ThongSoDoComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-thong-so-do div table .btn-danger'));
  title = element.all(by.css('jhi-thong-so-do div h2#page-heading span')).first();

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

export class ThongSoDoUpdatePage {
  pageTitle = element(by.id('jhi-thong-so-do-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  codeInput = element(by.id('field_code'));
  tenInput = element(by.id('field_ten'));
  dungSaiSelect = element(by.id('field_dungSai'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setCodeInput(code) {
    await this.codeInput.sendKeys(code);
  }

  async getCodeInput() {
    return await this.codeInput.getAttribute('value');
  }

  async setTenInput(ten) {
    await this.tenInput.sendKeys(ten);
  }

  async getTenInput() {
    return await this.tenInput.getAttribute('value');
  }

  async dungSaiSelectLastOption() {
    await this.dungSaiSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async dungSaiSelectOption(option) {
    await this.dungSaiSelect.sendKeys(option);
  }

  getDungSaiSelect(): ElementFinder {
    return this.dungSaiSelect;
  }

  async getDungSaiSelectedOption() {
    return await this.dungSaiSelect.element(by.css('option:checked')).getText();
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

export class ThongSoDoDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-thongSoDo-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-thongSoDo'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
