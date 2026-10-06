import { element, by, ElementFinder } from 'protractor';

export class ProTenMuaComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-pro-ten-mua div table .btn-danger'));
  title = element.all(by.css('jhi-pro-ten-mua div h2#page-heading span')).first();

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

export class ProTenMuaUpdatePage {
  pageTitle = element(by.id('jhi-pro-ten-mua-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  tenMuaInput = element(by.id('field_tenMua'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setTenMuaInput(tenMua) {
    await this.tenMuaInput.sendKeys(tenMua);
  }

  async getTenMuaInput() {
    return await this.tenMuaInput.getAttribute('value');
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

export class ProTenMuaDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-proTenMua-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-proTenMua'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
