import { element, by, ElementFinder } from 'protractor';

export class ThongSoDoSPSizeComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-thong-so-do-sp-size div table .btn-danger'));
  title = element.all(by.css('jhi-thong-so-do-sp-size div h2#page-heading span')).first();

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

export class ThongSoDoSPSizeUpdatePage {
  pageTitle = element(by.id('jhi-thong-so-do-sp-size-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  sizeInput = element(by.id('field_size'));
  cmThongSoChuanInput = element(by.id('field_cmThongSoChuan'));
  inchThongSoChuanInput = element(by.id('field_inchThongSoChuan'));
  cmThongSoChuanValueInput = element(by.id('field_cmThongSoChuanValue'));
  inchThongSoChuanValueInput = element(by.id('field_inchThongSoChuanValue'));
  thongSoDoSPSelect = element(by.id('field_thongSoDoSP'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setSizeInput(size) {
    await this.sizeInput.sendKeys(size);
  }

  async getSizeInput() {
    return await this.sizeInput.getAttribute('value');
  }

  async setCmThongSoChuanInput(cmThongSoChuan) {
    await this.cmThongSoChuanInput.sendKeys(cmThongSoChuan);
  }

  async getCmThongSoChuanInput() {
    return await this.cmThongSoChuanInput.getAttribute('value');
  }

  async setInchThongSoChuanInput(inchThongSoChuan) {
    await this.inchThongSoChuanInput.sendKeys(inchThongSoChuan);
  }

  async getInchThongSoChuanInput() {
    return await this.inchThongSoChuanInput.getAttribute('value');
  }

  async setCmThongSoChuanValueInput(cmThongSoChuanValue) {
    await this.cmThongSoChuanValueInput.sendKeys(cmThongSoChuanValue);
  }

  async getCmThongSoChuanValueInput() {
    return await this.cmThongSoChuanValueInput.getAttribute('value');
  }

  async setInchThongSoChuanValueInput(inchThongSoChuanValue) {
    await this.inchThongSoChuanValueInput.sendKeys(inchThongSoChuanValue);
  }

  async getInchThongSoChuanValueInput() {
    return await this.inchThongSoChuanValueInput.getAttribute('value');
  }

  async thongSoDoSPSelectLastOption() {
    await this.thongSoDoSPSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async thongSoDoSPSelectOption(option) {
    await this.thongSoDoSPSelect.sendKeys(option);
  }

  getThongSoDoSPSelect(): ElementFinder {
    return this.thongSoDoSPSelect;
  }

  async getThongSoDoSPSelectedOption() {
    return await this.thongSoDoSPSelect.element(by.css('option:checked')).getText();
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

export class ThongSoDoSPSizeDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-thongSoDoSPSize-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-thongSoDoSPSize'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
