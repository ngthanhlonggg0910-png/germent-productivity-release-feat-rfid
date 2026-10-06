import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GYCLKhacPhucComponentsPage, GYCLKhacPhucDeleteDialog, GYCLKhacPhucUpdatePage } from './gycl-khac-phuc.page-object';

const expect = chai.expect;

describe('GYCLKhacPhuc e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let gYCLKhacPhucComponentsPage: GYCLKhacPhucComponentsPage;
  let gYCLKhacPhucUpdatePage: GYCLKhacPhucUpdatePage;
  let gYCLKhacPhucDeleteDialog: GYCLKhacPhucDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load GYCLKhacPhucs', async () => {
    await navBarPage.goToEntity('gycl-khac-phuc');
    gYCLKhacPhucComponentsPage = new GYCLKhacPhucComponentsPage();
    await browser.wait(ec.visibilityOf(gYCLKhacPhucComponentsPage.title), 5000);
    expect(await gYCLKhacPhucComponentsPage.getTitle()).to.eq('ctApp.gYCLKhacPhuc.home.title');
  });

  it('should load create GYCLKhacPhuc page', async () => {
    await gYCLKhacPhucComponentsPage.clickOnCreateButton();
    gYCLKhacPhucUpdatePage = new GYCLKhacPhucUpdatePage();
    expect(await gYCLKhacPhucUpdatePage.getPageTitle()).to.eq('ctApp.gYCLKhacPhuc.home.createOrEditLabel');
    await gYCLKhacPhucUpdatePage.cancel();
  });

  it('should create and save GYCLKhacPhucs', async () => {
    const nbButtonsBeforeCreate = await gYCLKhacPhucComponentsPage.countDeleteButtons();

    await gYCLKhacPhucComponentsPage.clickOnCreateButton();
    await promise.all([
      gYCLKhacPhucUpdatePage.setCumIdInput('5'),
      gYCLKhacPhucUpdatePage.setTenCumInput('tenCum'),
      gYCLKhacPhucUpdatePage.setGopYInput('gopY'),
      gYCLKhacPhucUpdatePage.setKetQuaInput('ketQua'),
      gYCLKhacPhucUpdatePage.setNguoiTaoInput('nguoiTao'),
      gYCLKhacPhucUpdatePage.setUserIdInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      gYCLKhacPhucUpdatePage.setNgayTaoInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      gYCLKhacPhucUpdatePage.proGopYChatLuongSelectLastOption(),
      gYCLKhacPhucUpdatePage.thuVienLoiSelectLastOption(),
      gYCLKhacPhucUpdatePage.khacPhucLoiSelectLastOption()
    ]);
    expect(await gYCLKhacPhucUpdatePage.getCumIdInput()).to.eq('5', 'Expected cumId value to be equals to 5');
    expect(await gYCLKhacPhucUpdatePage.getTenCumInput()).to.eq('tenCum', 'Expected TenCum value to be equals to tenCum');
    expect(await gYCLKhacPhucUpdatePage.getGopYInput()).to.eq('gopY', 'Expected GopY value to be equals to gopY');
    expect(await gYCLKhacPhucUpdatePage.getKetQuaInput()).to.eq('ketQua', 'Expected KetQua value to be equals to ketQua');
    expect(await gYCLKhacPhucUpdatePage.getNguoiTaoInput()).to.eq('nguoiTao', 'Expected NguoiTao value to be equals to nguoiTao');
    expect(await gYCLKhacPhucUpdatePage.getUserIdInput()).to.contain(
      '2001-01-01T02:30',
      'Expected userId value to be equals to 2000-12-31'
    );
    expect(await gYCLKhacPhucUpdatePage.getNgayTaoInput()).to.contain(
      '2001-01-01T02:30',
      'Expected ngayTao value to be equals to 2000-12-31'
    );
    await gYCLKhacPhucUpdatePage.save();
    expect(await gYCLKhacPhucUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await gYCLKhacPhucComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last GYCLKhacPhuc', async () => {
    const nbButtonsBeforeDelete = await gYCLKhacPhucComponentsPage.countDeleteButtons();
    await gYCLKhacPhucComponentsPage.clickOnLastDeleteButton();

    gYCLKhacPhucDeleteDialog = new GYCLKhacPhucDeleteDialog();
    expect(await gYCLKhacPhucDeleteDialog.getDialogTitle()).to.eq('ctApp.gYCLKhacPhuc.delete.question');
    await gYCLKhacPhucDeleteDialog.clickOnConfirmButton();

    expect(await gYCLKhacPhucComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
