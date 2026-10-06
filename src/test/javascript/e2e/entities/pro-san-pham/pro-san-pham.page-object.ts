import { element, by, ElementFinder } from 'protractor';

export class ProSanPhamComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-pro-san-pham div table .btn-danger'));
  title = element.all(by.css('jhi-pro-san-pham div h2#page-heading span')).first();

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

export class ProSanPhamUpdatePage {
  pageTitle = element(by.id('jhi-pro-san-pham-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  maSanPhamInput = element(by.id('field_maSanPham'));
  maDonHangInput = element(by.id('field_maDonHang'));
  poInput = element(by.id('field_po'));
  soLuongSanXuatInput = element(by.id('field_soLuongSanXuat'));
  thongSoValue1Input = element(by.id('field_thongSoValue1'));
  thongSoCaption1Input = element(by.id('field_thongSoCaption1'));
  thongSoCheck1Input = element(by.id('field_thongSoCheck1'));
  thongSoValue2Input = element(by.id('field_thongSoValue2'));
  thongSoCaption2Input = element(by.id('field_thongSoCaption2'));
  thongSoCheck2Input = element(by.id('field_thongSoCheck2'));
  thongSoValue3Input = element(by.id('field_thongSoValue3'));
  thongSoCaption3Input = element(by.id('field_thongSoCaption3'));
  thongSoCheck3Input = element(by.id('field_thongSoCheck3'));
  thongSoValue4Input = element(by.id('field_thongSoValue4'));
  thongSoCaption4Input = element(by.id('field_thongSoCaption4'));
  thongSoCheck4Input = element(by.id('field_thongSoCheck4'));
  thongSoValue5Input = element(by.id('field_thongSoValue5'));
  thongSoCaption5Input = element(by.id('field_thongSoCaption5'));
  thongSoCheck5Input = element(by.id('field_thongSoCheck5'));
  thongSoValue6Input = element(by.id('field_thongSoValue6'));
  thongSoCaption6Input = element(by.id('field_thongSoCaption6'));
  thongSoCheck6Input = element(by.id('field_thongSoCheck6'));
  thongSoValue7Input = element(by.id('field_thongSoValue7'));
  thongSoCaption7Input = element(by.id('field_thongSoCaption7'));
  thongSoCheck7Input = element(by.id('field_thongSoCheck7'));
  thongSoValue8Input = element(by.id('field_thongSoValue8'));
  thongSoCaption8Input = element(by.id('field_thongSoCaption8'));
  thongSoCheck8Input = element(by.id('field_thongSoCheck8'));
  thongSoValue9Input = element(by.id('field_thongSoValue9'));
  thongSoCaption9Input = element(by.id('field_thongSoCaption9'));
  thongSoCheck9Input = element(by.id('field_thongSoCheck9'));
  thongSoValue10Input = element(by.id('field_thongSoValue10'));
  thongSoCaption10Input = element(by.id('field_thongSoCaption10'));
  thongSoCheck10Input = element(by.id('field_thongSoCheck10'));
  colorInput = element(by.id('field_color'));
  hinhMatTruocURLInput = element(by.id('field_hinhMatTruocURL'));
  hinhMatSauURLInput = element(by.id('field_hinhMatSauURL'));
  proKhachHangSelect = element(by.id('field_proKhachHang'));
  proChungLoaiSanPhamSelect = element(by.id('field_proChungLoaiSanPham'));
  proTenMuaSelect = element(by.id('field_proTenMua'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setMaSanPhamInput(maSanPham) {
    await this.maSanPhamInput.sendKeys(maSanPham);
  }

  async getMaSanPhamInput() {
    return await this.maSanPhamInput.getAttribute('value');
  }

  async setMaDonHangInput(maDonHang) {
    await this.maDonHangInput.sendKeys(maDonHang);
  }

  async getMaDonHangInput() {
    return await this.maDonHangInput.getAttribute('value');
  }

  async setPoInput(po) {
    await this.poInput.sendKeys(po);
  }

  async getPoInput() {
    return await this.poInput.getAttribute('value');
  }

  async setSoLuongSanXuatInput(soLuongSanXuat) {
    await this.soLuongSanXuatInput.sendKeys(soLuongSanXuat);
  }

  async getSoLuongSanXuatInput() {
    return await this.soLuongSanXuatInput.getAttribute('value');
  }

  async setThongSoValue1Input(thongSoValue1) {
    await this.thongSoValue1Input.sendKeys(thongSoValue1);
  }

  async getThongSoValue1Input() {
    return await this.thongSoValue1Input.getAttribute('value');
  }

  async setThongSoCaption1Input(thongSoCaption1) {
    await this.thongSoCaption1Input.sendKeys(thongSoCaption1);
  }

  async getThongSoCaption1Input() {
    return await this.thongSoCaption1Input.getAttribute('value');
  }

  getThongSoCheck1Input() {
    return this.thongSoCheck1Input;
  }
  async setThongSoValue2Input(thongSoValue2) {
    await this.thongSoValue2Input.sendKeys(thongSoValue2);
  }

  async getThongSoValue2Input() {
    return await this.thongSoValue2Input.getAttribute('value');
  }

  async setThongSoCaption2Input(thongSoCaption2) {
    await this.thongSoCaption2Input.sendKeys(thongSoCaption2);
  }

  async getThongSoCaption2Input() {
    return await this.thongSoCaption2Input.getAttribute('value');
  }

  getThongSoCheck2Input() {
    return this.thongSoCheck2Input;
  }
  async setThongSoValue3Input(thongSoValue3) {
    await this.thongSoValue3Input.sendKeys(thongSoValue3);
  }

  async getThongSoValue3Input() {
    return await this.thongSoValue3Input.getAttribute('value');
  }

  async setThongSoCaption3Input(thongSoCaption3) {
    await this.thongSoCaption3Input.sendKeys(thongSoCaption3);
  }

  async getThongSoCaption3Input() {
    return await this.thongSoCaption3Input.getAttribute('value');
  }

  getThongSoCheck3Input() {
    return this.thongSoCheck3Input;
  }
  async setThongSoValue4Input(thongSoValue4) {
    await this.thongSoValue4Input.sendKeys(thongSoValue4);
  }

  async getThongSoValue4Input() {
    return await this.thongSoValue4Input.getAttribute('value');
  }

  async setThongSoCaption4Input(thongSoCaption4) {
    await this.thongSoCaption4Input.sendKeys(thongSoCaption4);
  }

  async getThongSoCaption4Input() {
    return await this.thongSoCaption4Input.getAttribute('value');
  }

  getThongSoCheck4Input() {
    return this.thongSoCheck4Input;
  }
  async setThongSoValue5Input(thongSoValue5) {
    await this.thongSoValue5Input.sendKeys(thongSoValue5);
  }

  async getThongSoValue5Input() {
    return await this.thongSoValue5Input.getAttribute('value');
  }

  async setThongSoCaption5Input(thongSoCaption5) {
    await this.thongSoCaption5Input.sendKeys(thongSoCaption5);
  }

  async getThongSoCaption5Input() {
    return await this.thongSoCaption5Input.getAttribute('value');
  }

  getThongSoCheck5Input() {
    return this.thongSoCheck5Input;
  }
  async setThongSoValue6Input(thongSoValue6) {
    await this.thongSoValue6Input.sendKeys(thongSoValue6);
  }

  async getThongSoValue6Input() {
    return await this.thongSoValue6Input.getAttribute('value');
  }

  async setThongSoCaption6Input(thongSoCaption6) {
    await this.thongSoCaption6Input.sendKeys(thongSoCaption6);
  }

  async getThongSoCaption6Input() {
    return await this.thongSoCaption6Input.getAttribute('value');
  }

  getThongSoCheck6Input() {
    return this.thongSoCheck6Input;
  }
  async setThongSoValue7Input(thongSoValue7) {
    await this.thongSoValue7Input.sendKeys(thongSoValue7);
  }

  async getThongSoValue7Input() {
    return await this.thongSoValue7Input.getAttribute('value');
  }

  async setThongSoCaption7Input(thongSoCaption7) {
    await this.thongSoCaption7Input.sendKeys(thongSoCaption7);
  }

  async getThongSoCaption7Input() {
    return await this.thongSoCaption7Input.getAttribute('value');
  }

  getThongSoCheck7Input() {
    return this.thongSoCheck7Input;
  }
  async setThongSoValue8Input(thongSoValue8) {
    await this.thongSoValue8Input.sendKeys(thongSoValue8);
  }

  async getThongSoValue8Input() {
    return await this.thongSoValue8Input.getAttribute('value');
  }

  async setThongSoCaption8Input(thongSoCaption8) {
    await this.thongSoCaption8Input.sendKeys(thongSoCaption8);
  }

  async getThongSoCaption8Input() {
    return await this.thongSoCaption8Input.getAttribute('value');
  }

  getThongSoCheck8Input() {
    return this.thongSoCheck8Input;
  }
  async setThongSoValue9Input(thongSoValue9) {
    await this.thongSoValue9Input.sendKeys(thongSoValue9);
  }

  async getThongSoValue9Input() {
    return await this.thongSoValue9Input.getAttribute('value');
  }

  async setThongSoCaption9Input(thongSoCaption9) {
    await this.thongSoCaption9Input.sendKeys(thongSoCaption9);
  }

  async getThongSoCaption9Input() {
    return await this.thongSoCaption9Input.getAttribute('value');
  }

  getThongSoCheck9Input() {
    return this.thongSoCheck9Input;
  }
  async setThongSoValue10Input(thongSoValue10) {
    await this.thongSoValue10Input.sendKeys(thongSoValue10);
  }

  async getThongSoValue10Input() {
    return await this.thongSoValue10Input.getAttribute('value');
  }

  async setThongSoCaption10Input(thongSoCaption10) {
    await this.thongSoCaption10Input.sendKeys(thongSoCaption10);
  }

  async getThongSoCaption10Input() {
    return await this.thongSoCaption10Input.getAttribute('value');
  }

  getThongSoCheck10Input() {
    return this.thongSoCheck10Input;
  }
  async setColorInput(color) {
    await this.colorInput.sendKeys(color);
  }

  async getColorInput() {
    return await this.colorInput.getAttribute('value');
  }

  async setHinhMatTruocURLInput(hinhMatTruocURL) {
    await this.hinhMatTruocURLInput.sendKeys(hinhMatTruocURL);
  }

  async getHinhMatTruocURLInput() {
    return await this.hinhMatTruocURLInput.getAttribute('value');
  }

  async setHinhMatSauURLInput(hinhMatSauURL) {
    await this.hinhMatSauURLInput.sendKeys(hinhMatSauURL);
  }

  async getHinhMatSauURLInput() {
    return await this.hinhMatSauURLInput.getAttribute('value');
  }

  async proKhachHangSelectLastOption() {
    await this.proKhachHangSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async proKhachHangSelectOption(option) {
    await this.proKhachHangSelect.sendKeys(option);
  }

  getProKhachHangSelect(): ElementFinder {
    return this.proKhachHangSelect;
  }

  async getProKhachHangSelectedOption() {
    return await this.proKhachHangSelect.element(by.css('option:checked')).getText();
  }

  async proChungLoaiSanPhamSelectLastOption() {
    await this.proChungLoaiSanPhamSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async proChungLoaiSanPhamSelectOption(option) {
    await this.proChungLoaiSanPhamSelect.sendKeys(option);
  }

  getProChungLoaiSanPhamSelect(): ElementFinder {
    return this.proChungLoaiSanPhamSelect;
  }

  async getProChungLoaiSanPhamSelectedOption() {
    return await this.proChungLoaiSanPhamSelect.element(by.css('option:checked')).getText();
  }

  async proTenMuaSelectLastOption() {
    await this.proTenMuaSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async proTenMuaSelectOption(option) {
    await this.proTenMuaSelect.sendKeys(option);
  }

  getProTenMuaSelect(): ElementFinder {
    return this.proTenMuaSelect;
  }

  async getProTenMuaSelectedOption() {
    return await this.proTenMuaSelect.element(by.css('option:checked')).getText();
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

export class ProSanPhamDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-proSanPham-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-proSanPham'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
