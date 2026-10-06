import { element, by, ElementFinder } from 'protractor';

export class ThongSoThucThiComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-thong-so-thuc-thi div table .btn-danger'));
  title = element.all(by.css('jhi-thong-so-thuc-thi div h2#page-heading span')).first();

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

export class ThongSoThucThiUpdatePage {
  pageTitle = element(by.id('jhi-thong-so-thuc-thi-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  spIndexInput = element(by.id('field_spIndex'));
  thongSoDoSPSizeSelect = element(by.id('field_thongSoDoSPSize'));
  dungSaiSelect = element(by.id('field_dungSai'));
  proKeHoachSanXuatSelect = element(by.id('field_proKeHoachSanXuat'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setSpIndexInput(spIndex) {
    await this.spIndexInput.sendKeys(spIndex);
  }

  async getSpIndexInput() {
    return await this.spIndexInput.getAttribute('value');
  }

  async thongSoDoSPSizeSelectLastOption() {
    await this.thongSoDoSPSizeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async thongSoDoSPSizeSelectOption(option) {
    await this.thongSoDoSPSizeSelect.sendKeys(option);
  }

  getThongSoDoSPSizeSelect(): ElementFinder {
    return this.thongSoDoSPSizeSelect;
  }

  async getThongSoDoSPSizeSelectedOption() {
    return await this.thongSoDoSPSizeSelect.element(by.css('option:checked')).getText();
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

  async proKeHoachSanXuatSelectLastOption() {
    await this.proKeHoachSanXuatSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async proKeHoachSanXuatSelectOption(option) {
    await this.proKeHoachSanXuatSelect.sendKeys(option);
  }

  getProKeHoachSanXuatSelect(): ElementFinder {
    return this.proKeHoachSanXuatSelect;
  }

  async getProKeHoachSanXuatSelectedOption() {
    return await this.proKeHoachSanXuatSelect.element(by.css('option:checked')).getText();
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

export class ThongSoThucThiDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-thongSoThucThi-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-thongSoThucThi'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
