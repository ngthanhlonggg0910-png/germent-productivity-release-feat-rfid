import { element, by, ElementFinder } from 'protractor';

export class ProCongDoanComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-pro-cong-doan div table .btn-danger'));
  title = element.all(by.css('jhi-pro-cong-doan div h2#page-heading span')).first();

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

export class ProCongDoanUpdatePage {
  pageTitle = element(by.id('jhi-pro-cong-doan-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  tenInput = element(by.id('field_ten'));
  nhomCongDoanInput = element(by.id('field_nhomCongDoan'));
  proNhomCongDoanSelect = element(by.id('field_proNhomCongDoan'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setTenInput(ten) {
    await this.tenInput.sendKeys(ten);
  }

  async getTenInput() {
    return await this.tenInput.getAttribute('value');
  }

  async setNhomCongDoanInput(nhomCongDoan) {
    await this.nhomCongDoanInput.sendKeys(nhomCongDoan);
  }

  async getNhomCongDoanInput() {
    return await this.nhomCongDoanInput.getAttribute('value');
  }

  async proNhomCongDoanSelectLastOption() {
    await this.proNhomCongDoanSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async proNhomCongDoanSelectOption(option) {
    await this.proNhomCongDoanSelect.sendKeys(option);
  }

  getProNhomCongDoanSelect(): ElementFinder {
    return this.proNhomCongDoanSelect;
  }

  async getProNhomCongDoanSelectedOption() {
    return await this.proNhomCongDoanSelect.element(by.css('option:checked')).getText();
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

export class ProCongDoanDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-proCongDoan-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-proCongDoan'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
