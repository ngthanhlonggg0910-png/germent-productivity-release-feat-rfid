import { element, by, ElementFinder } from 'protractor';

export class ProAqlComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-pro-aql div table .btn-danger'));
  title = element.all(by.css('jhi-pro-aql div h2#page-heading span')).first();

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

export class ProAqlUpdatePage {
  pageTitle = element(by.id('jhi-pro-aql-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  aqlInput = element(by.id('field_aql'));
  soLuongDonHangTuInput = element(by.id('field_soLuongDonHangTu'));
  soLuongDonHangDenInput = element(by.id('field_soLuongDonHangDen'));
  soLuongKiemInput = element(by.id('field_soLuongKiem'));
  soLuongSeFailInput = element(by.id('field_soLuongSeFail'));
  soLuongSePassInput = element(by.id('field_soLuongSePass'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setAqlInput(aql) {
    await this.aqlInput.sendKeys(aql);
  }

  async getAqlInput() {
    return await this.aqlInput.getAttribute('value');
  }

  async setSoLuongDonHangTuInput(soLuongDonHangTu) {
    await this.soLuongDonHangTuInput.sendKeys(soLuongDonHangTu);
  }

  async getSoLuongDonHangTuInput() {
    return await this.soLuongDonHangTuInput.getAttribute('value');
  }

  async setSoLuongDonHangDenInput(soLuongDonHangDen) {
    await this.soLuongDonHangDenInput.sendKeys(soLuongDonHangDen);
  }

  async getSoLuongDonHangDenInput() {
    return await this.soLuongDonHangDenInput.getAttribute('value');
  }

  async setSoLuongKiemInput(soLuongKiem) {
    await this.soLuongKiemInput.sendKeys(soLuongKiem);
  }

  async getSoLuongKiemInput() {
    return await this.soLuongKiemInput.getAttribute('value');
  }

  async setSoLuongSeFailInput(soLuongSeFail) {
    await this.soLuongSeFailInput.sendKeys(soLuongSeFail);
  }

  async getSoLuongSeFailInput() {
    return await this.soLuongSeFailInput.getAttribute('value');
  }

  async setSoLuongSePassInput(soLuongSePass) {
    await this.soLuongSePassInput.sendKeys(soLuongSePass);
  }

  async getSoLuongSePassInput() {
    return await this.soLuongSePassInput.getAttribute('value');
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

export class ProAqlDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-proAql-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-proAql'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
