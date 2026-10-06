import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProChuyenComponentsPage, ProChuyenDeleteDialog, ProChuyenUpdatePage } from './pro-chuyen.page-object';

const expect = chai.expect;

describe('ProChuyen e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let proChuyenComponentsPage: ProChuyenComponentsPage;
  let proChuyenUpdatePage: ProChuyenUpdatePage;
  let proChuyenDeleteDialog: ProChuyenDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProChuyens', async () => {
    await navBarPage.goToEntity('pro-chuyen');
    proChuyenComponentsPage = new ProChuyenComponentsPage();
    await browser.wait(ec.visibilityOf(proChuyenComponentsPage.title), 5000);
    expect(await proChuyenComponentsPage.getTitle()).to.eq('ctApp.proChuyen.home.title');
  });

  it('should load create ProChuyen page', async () => {
    await proChuyenComponentsPage.clickOnCreateButton();
    proChuyenUpdatePage = new ProChuyenUpdatePage();
    expect(await proChuyenUpdatePage.getPageTitle()).to.eq('ctApp.proChuyen.home.createOrEditLabel');
    await proChuyenUpdatePage.cancel();
  });

  it('should create and save ProChuyens', async () => {
    const nbButtonsBeforeCreate = await proChuyenComponentsPage.countDeleteButtons();

    await proChuyenComponentsPage.clickOnCreateButton();
    await promise.all([
      proChuyenUpdatePage.setTenInput('ten'),
      proChuyenUpdatePage.setSttInput('5'),
      proChuyenUpdatePage.setTenLoaiChuyenInput('tenLoaiChuyen'),
      proChuyenUpdatePage.proLoaiChuyenSelectLastOption(),
      proChuyenUpdatePage.proNhaMaySelectLastOption()
    ]);
    expect(await proChuyenUpdatePage.getTenInput()).to.eq('ten', 'Expected Ten value to be equals to ten');
    expect(await proChuyenUpdatePage.getSttInput()).to.eq('5', 'Expected stt value to be equals to 5');
    expect(await proChuyenUpdatePage.getTenLoaiChuyenInput()).to.eq(
      'tenLoaiChuyen',
      'Expected TenLoaiChuyen value to be equals to tenLoaiChuyen'
    );
    await proChuyenUpdatePage.save();
    expect(await proChuyenUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await proChuyenComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ProChuyen', async () => {
    const nbButtonsBeforeDelete = await proChuyenComponentsPage.countDeleteButtons();
    await proChuyenComponentsPage.clickOnLastDeleteButton();

    proChuyenDeleteDialog = new ProChuyenDeleteDialog();
    expect(await proChuyenDeleteDialog.getDialogTitle()).to.eq('ctApp.proChuyen.delete.question');
    await proChuyenDeleteDialog.clickOnConfirmButton();

    expect(await proChuyenComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
