import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProIssueCumLoiComponentsPage, ProIssueCumLoiDeleteDialog, ProIssueCumLoiUpdatePage } from './pro-issue-cum-loi.page-object';

const expect = chai.expect;

describe('ProIssueCumLoi e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let proIssueCumLoiComponentsPage: ProIssueCumLoiComponentsPage;
  let proIssueCumLoiUpdatePage: ProIssueCumLoiUpdatePage;
  let proIssueCumLoiDeleteDialog: ProIssueCumLoiDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProIssueCumLois', async () => {
    await navBarPage.goToEntity('pro-issue-cum-loi');
    proIssueCumLoiComponentsPage = new ProIssueCumLoiComponentsPage();
    await browser.wait(ec.visibilityOf(proIssueCumLoiComponentsPage.title), 5000);
    expect(await proIssueCumLoiComponentsPage.getTitle()).to.eq('ctApp.proIssueCumLoi.home.title');
  });

  it('should load create ProIssueCumLoi page', async () => {
    await proIssueCumLoiComponentsPage.clickOnCreateButton();
    proIssueCumLoiUpdatePage = new ProIssueCumLoiUpdatePage();
    expect(await proIssueCumLoiUpdatePage.getPageTitle()).to.eq('ctApp.proIssueCumLoi.home.createOrEditLabel');
    await proIssueCumLoiUpdatePage.cancel();
  });

  it('should create and save ProIssueCumLois', async () => {
    const nbButtonsBeforeCreate = await proIssueCumLoiComponentsPage.countDeleteButtons();

    await proIssueCumLoiComponentsPage.clickOnCreateButton();
    await promise.all([proIssueCumLoiUpdatePage.setTenInput('ten'), proIssueCumLoiUpdatePage.setMauInput('mau')]);
    expect(await proIssueCumLoiUpdatePage.getTenInput()).to.eq('ten', 'Expected Ten value to be equals to ten');
    expect(await proIssueCumLoiUpdatePage.getMauInput()).to.eq('mau', 'Expected Mau value to be equals to mau');
    const selectedToTruong = proIssueCumLoiUpdatePage.getToTruongInput();
    if (await selectedToTruong.isSelected()) {
      await proIssueCumLoiUpdatePage.getToTruongInput().click();
      expect(await proIssueCumLoiUpdatePage.getToTruongInput().isSelected(), 'Expected toTruong not to be selected').to.be.false;
    } else {
      await proIssueCumLoiUpdatePage.getToTruongInput().click();
      expect(await proIssueCumLoiUpdatePage.getToTruongInput().isSelected(), 'Expected toTruong to be selected').to.be.true;
    }
    const selectedKyThuat = proIssueCumLoiUpdatePage.getKyThuatInput();
    if (await selectedKyThuat.isSelected()) {
      await proIssueCumLoiUpdatePage.getKyThuatInput().click();
      expect(await proIssueCumLoiUpdatePage.getKyThuatInput().isSelected(), 'Expected kyThuat not to be selected').to.be.false;
    } else {
      await proIssueCumLoiUpdatePage.getKyThuatInput().click();
      expect(await proIssueCumLoiUpdatePage.getKyThuatInput().isSelected(), 'Expected kyThuat to be selected').to.be.true;
    }
    const selectedQa = proIssueCumLoiUpdatePage.getQaInput();
    if (await selectedQa.isSelected()) {
      await proIssueCumLoiUpdatePage.getQaInput().click();
      expect(await proIssueCumLoiUpdatePage.getQaInput().isSelected(), 'Expected qa not to be selected').to.be.false;
    } else {
      await proIssueCumLoiUpdatePage.getQaInput().click();
      expect(await proIssueCumLoiUpdatePage.getQaInput().isSelected(), 'Expected qa to be selected').to.be.true;
    }
    await proIssueCumLoiUpdatePage.save();
    expect(await proIssueCumLoiUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await proIssueCumLoiComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last ProIssueCumLoi', async () => {
    const nbButtonsBeforeDelete = await proIssueCumLoiComponentsPage.countDeleteButtons();
    await proIssueCumLoiComponentsPage.clickOnLastDeleteButton();

    proIssueCumLoiDeleteDialog = new ProIssueCumLoiDeleteDialog();
    expect(await proIssueCumLoiDeleteDialog.getDialogTitle()).to.eq('ctApp.proIssueCumLoi.delete.question');
    await proIssueCumLoiDeleteDialog.clickOnConfirmButton();

    expect(await proIssueCumLoiComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
