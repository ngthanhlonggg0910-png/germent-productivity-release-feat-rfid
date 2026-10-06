import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProLoaiChuyenComponentsPage, ProLoaiChuyenDeleteDialog, ProLoaiChuyenUpdatePage } from './pro-loai-chuyen.page-object';

const expect = chai.expect;

describe('ProLoaiChuyen e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let proLoaiChuyenComponentsPage: ProLoaiChuyenComponentsPage;
  let proLoaiChuyenUpdatePage: ProLoaiChuyenUpdatePage;
  let proLoaiChuyenDeleteDialog: ProLoaiChuyenDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProLoaiChuyens', async () => {
    await navBarPage.goToEntity('pro-loai-chuyen');
    proLoaiChuyenComponentsPage = new ProLoaiChuyenComponentsPage();
    await browser.wait(ec.visibilityOf(proLoaiChuyenComponentsPage.title), 5000);
    expect(await proLoaiChuyenComponentsPage.getTitle()).to.eq('ctApp.proLoaiChuyen.home.title');
  });

  it('should load create ProLoaiChuyen page', async () => {
    await proLoaiChuyenComponentsPage.clickOnCreateButton();
    proLoaiChuyenUpdatePage = new ProLoaiChuyenUpdatePage();
    expect(await proLoaiChuyenUpdatePage.getPageTitle()).to.eq('ctApp.proLoaiChuyen.home.createOrEditLabel');
    await proLoaiChuyenUpdatePage.cancel();
  });

  it('should create and save ProLoaiChuyens', async () => {
    const nbButtonsBeforeCreate = await proLoaiChuyenComponentsPage.countDeleteButtons();

    await proLoaiChuyenComponentsPage.clickOnCreateButton();
    await promise.all([proLoaiChuyenUpdatePage.setTenInput('ten')]);
    expect(await proLoaiChuyenUpdatePage.getTenInput()).to.eq('ten', 'Expected Ten value to be equals to ten');
    await proLoaiChuyenUpdatePage.save();
    expect(await proLoaiChuyenUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await proLoaiChuyenComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ProLoaiChuyen', async () => {
    const nbButtonsBeforeDelete = await proLoaiChuyenComponentsPage.countDeleteButtons();
    await proLoaiChuyenComponentsPage.clickOnLastDeleteButton();

    proLoaiChuyenDeleteDialog = new ProLoaiChuyenDeleteDialog();
    expect(await proLoaiChuyenDeleteDialog.getDialogTitle()).to.eq('ctApp.proLoaiChuyen.delete.question');
    await proLoaiChuyenDeleteDialog.clickOnConfirmButton();

    expect(await proLoaiChuyenComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
