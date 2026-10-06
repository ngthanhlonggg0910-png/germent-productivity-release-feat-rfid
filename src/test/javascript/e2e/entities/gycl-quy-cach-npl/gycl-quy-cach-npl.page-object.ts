import { element, by, ElementFinder } from 'protractor';

export class GYCLQuyCachNPLComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-gycl-quy-cach-npl div table .btn-danger'));
  title = element.all(by.css('jhi-gycl-quy-cach-npl div h2#page-heading span')).first();

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

export class GYCLQuyCachNPLUpdatePage {
  pageTitle = element(by.id('jhi-gycl-quy-cach-npl-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  tenNPLInput = element(by.id('field_tenNPL'));
  nguoiTaoInput = element(by.id('field_nguoiTao'));
  ngayTaoInput = element(by.id('field_ngayTao'));
  userIdInput = element(by.id('field_userId'));
  yesNoInput = element(by.id('field_yesNo'));
  nAInput = element(by.id('field_nA'));
  proGopYChatLuongSelect = element(by.id('field_proGopYChatLuong'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setTenNPLInput(tenNPL) {
    await this.tenNPLInput.sendKeys(tenNPL);
  }

  async getTenNPLInput() {
    return await this.tenNPLInput.getAttribute('value');
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

  getYesNoInput() {
    return this.yesNoInput;
  }
  async setNAInput(nA) {
    await this.nAInput.sendKeys(nA);
  }

  async getNAInput() {
    return await this.nAInput.getAttribute('value');
  }

  async proGopYChatLuongSelectLastOption() {
    await this.proGopYChatLuongSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async proGopYChatLuongSelectOption(option) {
    await this.proGopYChatLuongSelect.sendKeys(option);
  }

  getProGopYChatLuongSelect(): ElementFinder {
    return this.proGopYChatLuongSelect;
  }

  async getProGopYChatLuongSelectedOption() {
    return await this.proGopYChatLuongSelect.element(by.css('option:checked')).getText();
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

export class GYCLQuyCachNPLDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-gYCLQuyCachNPL-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-gYCLQuyCachNPL'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
