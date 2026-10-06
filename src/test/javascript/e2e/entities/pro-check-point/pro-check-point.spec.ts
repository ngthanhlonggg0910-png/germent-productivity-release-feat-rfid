import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProCheckPointComponentsPage, ProCheckPointDeleteDialog, ProCheckPointUpdatePage } from './pro-check-point.page-object';

const expect = chai.expect;

describe('ProCheckPoint e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let proCheckPointComponentsPage: ProCheckPointComponentsPage;
  let proCheckPointUpdatePage: ProCheckPointUpdatePage;
  let proCheckPointDeleteDialog: ProCheckPointDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProCheckPoints', async () => {
    await navBarPage.goToEntity('pro-check-point');
    proCheckPointComponentsPage = new ProCheckPointComponentsPage();
    await browser.wait(ec.visibilityOf(proCheckPointComponentsPage.title), 5000);
    expect(await proCheckPointComponentsPage.getTitle()).to.eq('ctApp.proCheckPoint.home.title');
  });

  it('should load create ProCheckPoint page', async () => {
    await proCheckPointComponentsPage.clickOnCreateButton();
    proCheckPointUpdatePage = new ProCheckPointUpdatePage();
    expect(await proCheckPointUpdatePage.getPageTitle()).to.eq('ctApp.proCheckPoint.home.createOrEditLabel');
    await proCheckPointUpdatePage.cancel();
  });

  it('should create and save ProCheckPoints', async () => {
    const nbButtonsBeforeCreate = await proCheckPointComponentsPage.countDeleteButtons();

    await proCheckPointComponentsPage.clickOnCreateButton();
    await promise.all([
      proCheckPointUpdatePage.setTenInput('ten'),
      proCheckPointUpdatePage.proChuyenSelectLastOption(),
      proCheckPointUpdatePage.proCongDoanSelectLastOption()
    ]);
    expect(await proCheckPointUpdatePage.getTenInput()).to.eq('ten', 'Expected Ten value to be equals to ten');
    await proCheckPointUpdatePage.save();
    expect(await proCheckPointUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await proCheckPointComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ProCheckPoint', async () => {
    const nbButtonsBeforeDelete = await proCheckPointComponentsPage.countDeleteButtons();
    await proCheckPointComponentsPage.clickOnLastDeleteButton();

    proCheckPointDeleteDialog = new ProCheckPointDeleteDialog();
    expect(await proCheckPointDeleteDialog.getDialogTitle()).to.eq('ctApp.proCheckPoint.delete.question');
    await proCheckPointDeleteDialog.clickOnConfirmButton();

    expect(await proCheckPointComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
