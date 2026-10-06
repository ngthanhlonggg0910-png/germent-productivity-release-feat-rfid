import { element, by, ElementFinder } from 'protractor';

export class QuyCachNPLComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-quy-cach-npl div table .btn-danger'));
  title = element.all(by.css('jhi-quy-cach-npl div h2#page-heading span')).first();

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

export class QuyCachNPLUpdatePage {
  pageTitle = element(by.id('jhi-quy-cach-npl-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  tenNPLInput = element(by.id('field_tenNPL'));
  maNPLInput = element(by.id('field_maNPL'));
  nguoiTaoInput = element(by.id('field_nguoiTao'));
  ngayTaoInput = element(by.id('field_ngayTao'));
  userIdInput = element(by.id('field_userId'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setTenNPLInput(tenNPL) {
    await this.tenNPLInput.sendKeys(tenNPL);
  }

  async getTenNPLInput() {
    return await this.tenNPLInput.getAttribute('value');
  }

  async setMaNPLInput(maNPL) {
    await this.maNPLInput.sendKeys(maNPL);
  }

  async getMaNPLInput() {
    return await this.maNPLInput.getAttribute('value');
  }

  async setNguoiTaoInput(nguoiTao) {
    await this.nguoiTaoInput.sendKeys(nguoiTao);
  }

  async getNguoiTaoInput() {
    return await this.nguoiTaoInput.getAttribute('value');
  }

  async setNgayTaoInput(ngayTao) {
    await this.ngayTaoInput.sendKeys(ngayTao);
  }

  async getNgayTaoInput() {
    return await this.ngayTaoInput.getAttribute('value');
  }

  async setUserIdInput(userId) {
    await this.userIdInput.sendKeys(userId);
  }

  async getUserIdInput() {
    return await this.userIdInput.getAttribute('value');
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

export class QuyCachNPLDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-quyCachNPL-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-quyCachNPL'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
