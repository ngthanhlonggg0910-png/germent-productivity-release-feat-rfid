import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SoLuongHangKiemComponentsPage, SoLuongHangKiemDeleteDialog, SoLuongHangKiemUpdatePage } from './so-luong-hang-kiem.page-object';

const expect = chai.expect;

describe('SoLuongHangKiem e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let soLuongHangKiemComponentsPage: SoLuongHangKiemComponentsPage;
  let soLuongHangKiemUpdatePage: SoLuongHangKiemUpdatePage;
  let soLuongHangKiemDeleteDialog: SoLuongHangKiemDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load SoLuongHangKiems', async () => {
    await navBarPage.goToEntity('so-luong-hang-kiem');
    soLuongHangKiemComponentsPage = new SoLuongHangKiemComponentsPage();
    await browser.wait(ec.visibilityOf(soLuongHangKiemComponentsPage.title), 5000);
    expect(await soLuongHangKiemComponentsPage.getTitle()).to.eq('ctApp.soLuongHangKiem.home.title');
  });

  it('should load create SoLuongHangKiem page', async () => {
    await soLuongHangKiemComponentsPage.clickOnCreateButton();
    soLuongHangKiemUpdatePage = new SoLuongHangKiemUpdatePage();
    expect(await soLuongHangKiemUpdatePage.getPageTitle()).to.eq('ctApp.soLuongHangKiem.home.createOrEditLabel');
    await soLuongHangKiemUpdatePage.cancel();
  });

  it('should create and save SoLuongHangKiems', async () => {
    const nbButtonsBeforeCreate = await soLuongHangKiemComponentsPage.countDeleteButtons();

    await soLuongHangKiemComponentsPage.clickOnCreateButton();
    await promise.all([soLuongHangKiemUpdatePage.setSoLuongInput('5')]);
    expect(await soLuongHangKiemUpdatePage.getSoLuongInput()).to.eq('5', 'Expected soLuong value to be equals to 5');
    await soLuongHangKiemUpdatePage.save();
    expect(await soLuongHangKiemUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await soLuongHangKiemComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last SoLuongHangKiem', async () => {
    const nbButtonsBeforeDelete = await soLuongHangKiemComponentsPage.countDeleteButtons();
    await soLuongHangKiemComponentsPage.clickOnLastDeleteButton();

    soLuongHangKiemDeleteDialog = new SoLuongHangKiemDeleteDialog();
    expect(await soLuongHangKiemDeleteDialog.getDialogTitle()).to.eq('ctApp.soLuongHangKiem.delete.question');
    await soLuongHangKiemDeleteDialog.clickOnConfirmButton();

    expect(await soLuongHangKiemComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
