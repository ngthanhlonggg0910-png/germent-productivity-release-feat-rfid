import { element, by, ElementFinder } from 'protractor';

export class ThongSoDoSPComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-thong-so-do-sp div table .btn-danger'));
  title = element.all(by.css('jhi-thong-so-do-sp div h2#page-heading span')).first();

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

export class ThongSoDoSPUpdatePage {
  pageTitle = element(by.id('jhi-thong-so-do-sp-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  noteInput = element(by.id('field_note'));
  thongSoDoSelect = element(by.id('field_thongSoDo'));
  dungSaiSelect = element(by.id('field_dungSai'));
  proSanPhamSelect = element(by.id('field_proSanPham'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNoteInput(note) {
    await this.noteInput.sendKeys(note);
  }

  async getNoteInput() {
    return await this.noteInput.getAttribute('value');
  }

  async thongSoDoSelectLastOption() {
    await this.thongSoDoSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async thongSoDoSelectOption(option) {
    await this.thongSoDoSelect.sendKeys(option);
  }

  getThongSoDoSelect(): ElementFinder {
    return this.thongSoDoSelect;
  }

  async getThongSoDoSelectedOption() {
    return await this.thongSoDoSelect.element(by.css('option:checked')).getText();
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

  async proSanPhamSelectLastOption() {
    await this.proSanPhamSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async proSanPhamSelectOption(option) {
    await this.proSanPhamSelect.sendKeys(option);
  }

  getProSanPhamSelect(): ElementFinder {
    return this.proSanPhamSelect;
  }

  async getProSanPhamSelectedOption() {
    return await this.proSanPhamSelect.element(by.css('option:checked')).getText();
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

export class ThongSoDoSPDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-thongSoDoSP-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-thongSoDoSP'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
