import { element, by, ElementFinder } from 'protractor';

export class ProKeHoachSanXuatComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-pro-ke-hoach-san-xuat div table .btn-danger'));
  title = element.all(by.css('jhi-pro-ke-hoach-san-xuat div h2#page-heading span')).first();

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

export class ProKeHoachSanXuatUpdatePage {
  pageTitle = element(by.id('jhi-pro-ke-hoach-san-xuat-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  ngaySanXuatInput = element(by.id('field_ngaySanXuat'));
  soLuongYeuCauInput = element(by.id('field_soLuongYeuCau'));
  tinhTrangInput = element(by.id('field_tinhTrang'));
  ghiChuInput = element(by.id('field_ghiChu'));
  samInput = element(by.id('field_sam'));
  lotNoInput = element(by.id('field_lotNo'));
  proChuyenSelect = element(by.id('field_proChuyen'));
  proSanPhamSelect = element(by.id('field_proSanPham'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNgaySanXuatInput(ngaySanXuat) {
    await this.ngaySanXuatInput.sendKeys(ngaySanXuat);
  }

  async getNgaySanXuatInput() {
    return await this.ngaySanXuatInput.getAttribute('value');
  }

  async setSoLuongYeuCauInput(soLuongYeuCau) {
    await this.soLuongYeuCauInput.sendKeys(soLuongYeuCau);
  }

  async getSoLuongYeuCauInput() {
    return await this.soLuongYeuCauInput.getAttribute('value');
  }

  async setTinhTrangInput(tinhTrang) {
    await this.tinhTrangInput.sendKeys(tinhTrang);
  }

  async getTinhTrangInput() {
    return await this.tinhTrangInput.getAttribute('value');
  }

  async setGhiChuInput(ghiChu) {
    await this.ghiChuInput.sendKeys(ghiChu);
  }

  async getGhiChuInput() {
    return await this.ghiChuInput.getAttribute('value');
  }

  async setSamInput(sam) {
    await this.samInput.sendKeys(sam);
  }

  async getSamInput() {
    return await this.samInput.getAttribute('value');
  }

  async setLotNoInput(lotNo) {
    await this.lotNoInput.sendKeys(lotNo);
  }

  async getLotNoInput() {
    return await this.lotNoInput.getAttribute('value');
  }

  async proChuyenSelectLastOption() {
    await this.proChuyenSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async proChuyenSelectOption(option) {
    await this.proChuyenSelect.sendKeys(option);
  }

  getProChuyenSelect(): ElementFinder {
    return this.proChuyenSelect;
  }

  async getProChuyenSelectedOption() {
    return await this.proChuyenSelect.element(by.css('option:checked')).getText();
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

export class ProKeHoachSanXuatDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-proKeHoachSanXuat-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-proKeHoachSanXuat'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
