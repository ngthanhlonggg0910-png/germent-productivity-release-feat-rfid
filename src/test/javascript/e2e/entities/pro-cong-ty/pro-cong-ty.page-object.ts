import { element, by, ElementFinder } from 'protractor';

export class ProCongTyComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-pro-cong-ty div table .btn-danger'));
  title = element.all(by.css('jhi-pro-cong-ty div h2#page-heading span')).first();

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

export class ProCongTyUpdatePage {
  pageTitle = element(by.id('jhi-pro-cong-ty-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  tenInput = element(by.id('field_ten'));
  diaChiInput = element(by.id('field_diaChi'));
  mstInput = element(by.id('field_mst'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setTenInput(ten) {
    await this.tenInput.sendKeys(ten);
  }

  async getTenInput() {
    return await this.tenInput.getAttribute('value');
  }

  async setDiaChiInput(diaChi) {
    await this.diaChiInput.sendKeys(diaChi);
  }

  async getDiaChiInput() {
    return await this.diaChiInput.getAttribute('value');
  }

  async setMstInput(mst) {
    await this.mstInput.sendKeys(mst);
  }

  async getMstInput() {
    return await this.mstInput.getAttribute('value');
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

export class ProCongTyDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-proCongTy-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-proCongTy'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
