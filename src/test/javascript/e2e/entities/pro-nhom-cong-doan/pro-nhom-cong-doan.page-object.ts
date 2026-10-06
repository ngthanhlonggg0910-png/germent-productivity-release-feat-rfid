import { element, by, ElementFinder } from 'protractor';

export class ProNhomCongDoanComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-pro-nhom-cong-doan div table .btn-danger'));
  title = element.all(by.css('jhi-pro-nhom-cong-doan div h2#page-heading span')).first();

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

export class ProNhomCongDoanUpdatePage {
  pageTitle = element(by.id('jhi-pro-nhom-cong-doan-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  tenInput = element(by.id('field_ten'));
  checkNangSuatInput = element(by.id('field_checkNangSuat'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setTenInput(ten) {
    await this.tenInput.sendKeys(ten);
  }

  async getTenInput() {
    return await this.tenInput.getAttribute('value');
  }

  getCheckNangSuatInput() {
    return this.checkNangSuatInput;
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

export class ProNhomCongDoanDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-proNhomCongDoan-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-proNhomCongDoan'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
