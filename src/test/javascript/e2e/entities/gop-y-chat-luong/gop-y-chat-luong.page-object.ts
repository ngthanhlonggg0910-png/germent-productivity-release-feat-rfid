import { element, by, ElementFinder } from 'protractor';

export class GopYChatLuongComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-gop-y-chat-luong div table .btn-danger'));
  title = element.all(by.css('jhi-gop-y-chat-luong div h2#page-heading span')).first();

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

export class GopYChatLuongUpdatePage {
  pageTitle = element(by.id('jhi-gop-y-chat-luong-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  cusIdInput = element(by.id('field_cusId'));
  cusNameInput = element(by.id('field_cusName'));
  chuyenIdInput = element(by.id('field_chuyenId'));
  chuyenNameInput = element(by.id('field_chuyenName'));
  maHangInput = element(by.id('field_maHang'));
  poInput = element(by.id('field_po'));
  poIdInput = element(by.id('field_poId'));
  colorInput = element(by.id('field_color'));
  sizeInput = element(by.id('field_size'));
  firstCutInput = element(by.id('field_firstCut'));
  inlineInput = element(by.id('field_inline'));
  lanBanHanhInput = element(by.id('field_lanBanHanh'));
  ngayBaoCaoInput = element(by.id('field_ngayBaoCao'));
  nguoiTaoInput = element(by.id('field_nguoiTao'));
  userIdInput = element(by.id('field_userId'));
  ngayTaoInput = element(by.id('field_ngayTao'));
  tenQuanLyXuongInput = element(by.id('field_tenQuanLyXuong'));
  tenKiThuatInput = element(by.id('field_tenKiThuat'));
  tenQanLyCatInput = element(by.id('field_tenQanLyCat'));
  tenQAInput = element(by.id('field_tenQA'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setCusIdInput(cusId) {
    await this.cusIdInput.sendKeys(cusId);
  }

  async getCusIdInput() {
    return await this.cusIdInput.getAttribute('value');
  }

  async setCusNameInput(cusName) {
    await this.cusNameInput.sendKeys(cusName);
  }

  async getCusNameInput() {
    return await this.cusNameInput.getAttribute('value');
  }

  async setChuyenIdInput(chuyenId) {
    await this.chuyenIdInput.sendKeys(chuyenId);
  }

  async getChuyenIdInput() {
    return await this.chuyenIdInput.getAttribute('value');
  }

  async setChuyenNameInput(chuyenName) {
    await this.chuyenNameInput.sendKeys(chuyenName);
  }

  async getChuyenNameInput() {
    return await this.chuyenNameInput.getAttribute('value');
  }

  async setMaHangInput(maHang) {
    await this.maHangInput.sendKeys(maHang);
  }

  async getMaHangInput() {
    return await this.maHangInput.getAttribute('value');
  }

  async setPoInput(po) {
    await this.poInput.sendKeys(po);
  }

  async getPoInput() {
    return await this.poInput.getAttribute('value');
  }

  async setPoIdInput(poId) {
    await this.poIdInput.sendKeys(poId);
  }

  async getPoIdInput() {
    return await this.poIdInput.getAttribute('value');
  }

  async setColorInput(color) {
    await this.colorInput.sendKeys(color);
  }

  async getColorInput() {
    return await this.colorInput.getAttribute('value');
  }

  async setSizeInput(size) {
    await this.sizeInput.sendKeys(size);
  }

  async getSizeInput() {
    return await this.sizeInput.getAttribute('value');
  }

  getFirstCutInput() {
    return this.firstCutInput;
  }
  getInlineInput() {
    return this.inlineInput;
  }
  async setLanBanHanhInput(lanBanHanh) {
    await this.lanBanHanhInput.sendKeys(lanBanHanh);
  }

  async getLanBanHanhInput() {
    return await this.lanBanHanhInput.getAttribute('value');
  }

  async setNgayBaoCaoInput(ngayBaoCao) {
    await this.ngayBaoCaoInput.sendKeys(ngayBaoCao);
  }

  async getNgayBaoCaoInput() {
    return await this.ngayBaoCaoInput.getAttribute('value');
  }

  async setNguoiTaoInput(nguoiTao) {
    await this.nguoiTaoInput.sendKeys(nguoiTao);
  }

  async getNguoiTaoInput() {
    return await this.nguoiTaoInput.getAttribute('value');
  }

  async setUserIdInput(userId) {
    await this.userIdInput.sendKeys(userId);
  }

  async getUserIdInput() {
    return await this.userIdInput.getAttribute('value');
  }

  async setNgayTaoInput(ngayTao) {
    await this.ngayTaoInput.sendKeys(ngayTao);
  }

  async getNgayTaoInput() {
    return await this.ngayTaoInput.getAttribute('value');
  }

  async setTenQuanLyXuongInput(tenQuanLyXuong) {
    await this.tenQuanLyXuongInput.sendKeys(tenQuanLyXuong);
  }

  async getTenQuanLyXuongInput() {
    return await this.tenQuanLyXuongInput.getAttribute('value');
  }

  async setTenKiThuatInput(tenKiThuat) {
    await this.tenKiThuatInput.sendKeys(tenKiThuat);
  }

  async getTenKiThuatInput() {
    return await this.tenKiThuatInput.getAttribute('value');
  }

  async setTenQanLyCatInput(tenQanLyCat) {
    await this.tenQanLyCatInput.sendKeys(tenQanLyCat);
  }

  async getTenQanLyCatInput() {
    return await this.tenQanLyCatInput.getAttribute('value');
  }

  async setTenQAInput(tenQA) {
    await this.tenQAInput.sendKeys(tenQA);
  }

  async getTenQAInput() {
    return await this.tenQAInput.getAttribute('value');
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

export class GopYChatLuongDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-gopYChatLuong-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-gopYChatLuong'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
