import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProIssueLoiComponentsPage, ProIssueLoiDeleteDialog, ProIssueLoiUpdatePage } from './pro-issue-loi.page-object';

const expect = chai.expect;

describe('ProIssueLoi e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let proIssueLoiComponentsPage: ProIssueLoiComponentsPage;
  let proIssueLoiUpdatePage: ProIssueLoiUpdatePage;
  let proIssueLoiDeleteDialog: ProIssueLoiDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProIssueLois', async () => {
    await navBarPage.goToEntity('pro-issue-loi');
    proIssueLoiComponentsPage = new ProIssueLoiComponentsPage();
    await browser.wait(ec.visibilityOf(proIssueLoiComponentsPage.title), 5000);
    expect(await proIssueLoiComponentsPage.getTitle()).to.eq('ctApp.proIssueLoi.home.title');
  });

  it('should load create ProIssueLoi page', async () => {
    await proIssueLoiComponentsPage.clickOnCreateButton();
    proIssueLoiUpdatePage = new ProIssueLoiUpdatePage();
    expect(await proIssueLoiUpdatePage.getPageTitle()).to.eq('ctApp.proIssueLoi.home.createOrEditLabel');
    await proIssueLoiUpdatePage.cancel();
  });

  it('should create and save ProIssueLois', async () => {
    const nbButtonsBeforeCreate = await proIssueLoiComponentsPage.countDeleteButtons();

    await proIssueLoiComponentsPage.clickOnCreateButton();
    await promise.all([
      proIssueLoiUpdatePage.setMaLoiInput('maLoi'),
      proIssueLoiUpdatePage.setCusCodeInput('cusCode'),
      proIssueLoiUpdatePage.setTenLoiInput('tenLoi'),
      proIssueLoiUpdatePage.setTenTiengAnhInput('tenTiengAnh'),
      proIssueLoiUpdatePage.setLabelInput('label'),
      proIssueLoiUpdatePage.setSttInput('5'),
      proIssueLoiUpdatePage.setMauSacInput('mauSac'),
      proIssueLoiUpdatePage.setGhiChuInput('ghiChu'),
      proIssueLoiUpdatePage.proIssueCumLoiSelectLastOption()
    ]);
    expect(await proIssueLoiUpdatePage.getMaLoiInput()).to.eq('maLoi', 'Expected MaLoi value to be equals to maLoi');
    expect(await proIssueLoiUpdatePage.getCusCodeInput()).to.eq('cusCode', 'Expected CusCode value to be equals to cusCode');
    expect(await proIssueLoiUpdatePage.getTenLoiInput()).to.eq('tenLoi', 'Expected TenLoi value to be equals to tenLoi');
    expect(await proIssueLoiUpdatePage.getTenTiengAnhInput()).to.eq(
      'tenTiengAnh',
      'Expected TenTiengAnh value to be equals to tenTiengAnh'
    );
    expect(await proIssueLoiUpdatePage.getLabelInput()).to.eq('label', 'Expected Label value to be equals to label');
    expect(await proIssueLoiUpdatePage.getSttInput()).to.eq('5', 'Expected stt value to be equals to 5');
    expect(await proIssueLoiUpdatePage.getMauSacInput()).to.eq('mauSac', 'Expected MauSac value to be equals to mauSac');
    expect(await proIssueLoiUpdatePage.getGhiChuInput()).to.eq('ghiChu', 'Expected GhiChu value to be equals to ghiChu');
    await proIssueLoiUpdatePage.save();
    expect(await proIssueLoiUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await proIssueLoiComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ProIssueLoi', async () => {
    const nbButtonsBeforeDelete = await proIssueLoiComponentsPage.countDeleteButtons();
    await proIssueLoiComponentsPage.clickOnLastDeleteButton();

    proIssueLoiDeleteDialog = new ProIssueLoiDeleteDialog();
    expect(await proIssueLoiDeleteDialog.getDialogTitle()).to.eq('ctApp.proIssueLoi.delete.question');
    await proIssueLoiDeleteDialog.clickOnConfirmButton();

    expect(await proIssueLoiComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
