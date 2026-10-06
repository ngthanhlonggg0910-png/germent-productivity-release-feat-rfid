import { element, by, ElementFinder } from 'protractor';

export class ProGioLamViecComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-pro-gio-lam-viec div table .btn-danger'));
  title = element.all(by.css('jhi-pro-gio-lam-viec div h2#page-heading span')).first();

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

export class ProGioLamViecUpdatePage {
  pageTitle = element(by.id('jhi-pro-gio-lam-viec-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  gioBatDauInput = element(by.id('field_gioBatDau'));
  gioKetThucInput = element(by.id('field_gioKetThuc'));
  gioLamSelect = element(by.id('field_gioLam'));
  thoiGianInput = element(by.id('field_thoiGian'));
  captionTVInput = element(by.id('field_captionTV'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setGioBatDauInput(gioBatDau) {
    await this.gioBatDauInput.sendKeys(gioBatDau);
  }

  async getGioBatDauInput() {
    return await this.gioBatDauInput.getAttribute('value');
  }

  async setGioKetThucInput(gioKetThuc) {
    await this.gioKetThucInput.sendKeys(gioKetThuc);
  }

  async getGioKetThucInput() {
    return await this.gioKetThucInput.getAttribute('value');
  }

  async setGioLamSelect(gioLam) {
    await this.gioLamSelect.sendKeys(gioLam);
  }

  async getGioLamSelect() {
    return await this.gioLamSelect.element(by.css('option:checked')).getText();
  }

  async gioLamSelectLastOption() {
    await this.gioLamSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setThoiGianInput(thoiGian) {
    await this.thoiGianInput.sendKeys(thoiGian);
  }

  async getThoiGianInput() {
    return await this.thoiGianInput.getAttribute('value');
  }

  async setCaptionTVInput(captionTV) {
    await this.captionTVInput.sendKeys(captionTV);
  }

  async getCaptionTVInput() {
    return await this.captionTVInput.getAttribute('value');
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

export class ProGioLamViecDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-proGioLamViec-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-proGioLamViec'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
