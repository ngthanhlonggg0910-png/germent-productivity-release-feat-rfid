import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProCongTyComponentsPage, ProCongTyDeleteDialog, ProCongTyUpdatePage } from './pro-cong-ty.page-object';

const expect = chai.expect;

describe('ProCongTy e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let proCongTyComponentsPage: ProCongTyComponentsPage;
  let proCongTyUpdatePage: ProCongTyUpdatePage;
  let proCongTyDeleteDialog: ProCongTyDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProCongTies', async () => {
    await navBarPage.goToEntity('pro-cong-ty');
    proCongTyComponentsPage = new ProCongTyComponentsPage();
    await browser.wait(ec.visibilityOf(proCongTyComponentsPage.title), 5000);
    expect(await proCongTyComponentsPage.getTitle()).to.eq('ctApp.proCongTy.home.title');
  });

  it('should load create ProCongTy page', async () => {
    await proCongTyComponentsPage.clickOnCreateButton();
    proCongTyUpdatePage = new ProCongTyUpdatePage();
    expect(await proCongTyUpdatePage.getPageTitle()).to.eq('ctApp.proCongTy.home.createOrEditLabel');
    await proCongTyUpdatePage.cancel();
  });

  it('should create and save ProCongTies', async () => {
    const nbButtonsBeforeCreate = await proCongTyComponentsPage.countDeleteButtons();

    await proCongTyComponentsPage.clickOnCreateButton();
    await promise.all([
      proCongTyUpdatePage.setTenInput('ten'),
      proCongTyUpdatePage.setDiaChiInput('diaChi'),
      proCongTyUpdatePage.setMstInput('mst')
    ]);
    expect(await proCongTyUpdatePage.getTenInput()).to.eq('ten', 'Expected Ten value to be equals to ten');
    expect(await proCongTyUpdatePage.getDiaChiInput()).to.eq('diaChi', 'Expected DiaChi value to be equals to diaChi');
    expect(await proCongTyUpdatePage.getMstInput()).to.eq('mst', 'Expected Mst value to be equals to mst');
    await proCongTyUpdatePage.save();
    expect(await proCongTyUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await proCongTyComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ProCongTy', async () => {
    const nbButtonsBeforeDelete = await proCongTyComponentsPage.countDeleteButtons();
    await proCongTyComponentsPage.clickOnLastDeleteButton();

    proCongTyDeleteDialog = new ProCongTyDeleteDialog();
    expect(await proCongTyDeleteDialog.getDialogTitle()).to.eq('ctApp.proCongTy.delete.question');
    await proCongTyDeleteDialog.clickOnConfirmButton();

    expect(await proCongTyComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
