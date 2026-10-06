import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProKhachHangComponentsPage, ProKhachHangDeleteDialog, ProKhachHangUpdatePage } from './pro-khach-hang.page-object';

const expect = chai.expect;

describe('ProKhachHang e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let proKhachHangComponentsPage: ProKhachHangComponentsPage;
  let proKhachHangUpdatePage: ProKhachHangUpdatePage;
  let proKhachHangDeleteDialog: ProKhachHangDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProKhachHangs', async () => {
    await navBarPage.goToEntity('pro-khach-hang');
    proKhachHangComponentsPage = new ProKhachHangComponentsPage();
    await browser.wait(ec.visibilityOf(proKhachHangComponentsPage.title), 5000);
    expect(await proKhachHangComponentsPage.getTitle()).to.eq('ctApp.proKhachHang.home.title');
  });

  it('should load create ProKhachHang page', async () => {
    await proKhachHangComponentsPage.clickOnCreateButton();
    proKhachHangUpdatePage = new ProKhachHangUpdatePage();
    expect(await proKhachHangUpdatePage.getPageTitle()).to.eq('ctApp.proKhachHang.home.createOrEditLabel');
    await proKhachHangUpdatePage.cancel();
  });

  it('should create and save ProKhachHangs', async () => {
    const nbButtonsBeforeCreate = await proKhachHangComponentsPage.countDeleteButtons();

    await proKhachHangComponentsPage.clickOnCreateButton();
    await promise.all([
      proKhachHangUpdatePage.setTenInput('ten'),
      proKhachHangUpdatePage.setNhomInput('nhom'),
      proKhachHangUpdatePage.setDiaChiInput('diaChi')
    ]);
    expect(await proKhachHangUpdatePage.getTenInput()).to.eq('ten', 'Expected Ten value to be equals to ten');
    expect(await proKhachHangUpdatePage.getNhomInput()).to.eq('nhom', 'Expected Nhom value to be equals to nhom');
    expect(await proKhachHangUpdatePage.getDiaChiInput()).to.eq('diaChi', 'Expected DiaChi value to be equals to diaChi');
    await proKhachHangUpdatePage.save();
    expect(await proKhachHangUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await proKhachHangComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ProKhachHang', async () => {
    const nbButtonsBeforeDelete = await proKhachHangComponentsPage.countDeleteButtons();
    await proKhachHangComponentsPage.clickOnLastDeleteButton();

    proKhachHangDeleteDialog = new ProKhachHangDeleteDialog();
    expect(await proKhachHangDeleteDialog.getDialogTitle()).to.eq('ctApp.proKhachHang.delete.question');
    await proKhachHangDeleteDialog.clickOnConfirmButton();

    expect(await proKhachHangComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
