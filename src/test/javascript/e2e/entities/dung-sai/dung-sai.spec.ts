import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { DungSaiComponentsPage, DungSaiDeleteDialog, DungSaiUpdatePage } from './dung-sai.page-object';

const expect = chai.expect;

describe('DungSai e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let dungSaiComponentsPage: DungSaiComponentsPage;
  let dungSaiUpdatePage: DungSaiUpdatePage;
  let dungSaiDeleteDialog: DungSaiDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load DungSais', async () => {
    await navBarPage.goToEntity('dung-sai');
    dungSaiComponentsPage = new DungSaiComponentsPage();
    await browser.wait(ec.visibilityOf(dungSaiComponentsPage.title), 5000);
    expect(await dungSaiComponentsPage.getTitle()).to.eq('ctApp.dungSai.home.title');
  });

  it('should load create DungSai page', async () => {
    await dungSaiComponentsPage.clickOnCreateButton();
    dungSaiUpdatePage = new DungSaiUpdatePage();
    expect(await dungSaiUpdatePage.getPageTitle()).to.eq('ctApp.dungSai.home.createOrEditLabel');
    await dungSaiUpdatePage.cancel();
  });

  it('should create and save DungSais', async () => {
    const nbButtonsBeforeCreate = await dungSaiComponentsPage.countDeleteButtons();

    await dungSaiComponentsPage.clickOnCreateButton();
    await promise.all([
      dungSaiUpdatePage.setCmLabelInput('cmLabel'),
      dungSaiUpdatePage.setInchLabelInput('inchLabel'),
      dungSaiUpdatePage.setCmValueInput('5'),
      dungSaiUpdatePage.setInchValueInput('5')
    ]);
    expect(await dungSaiUpdatePage.getCmLabelInput()).to.eq('cmLabel', 'Expected CmLabel value to be equals to cmLabel');
    expect(await dungSaiUpdatePage.getInchLabelInput()).to.eq('inchLabel', 'Expected InchLabel value to be equals to inchLabel');
    expect(await dungSaiUpdatePage.getCmValueInput()).to.eq('5', 'Expected cmValue value to be equals to 5');
    expect(await dungSaiUpdatePage.getInchValueInput()).to.eq('5', 'Expected inchValue value to be equals to 5');
    await dungSaiUpdatePage.save();
    expect(await dungSaiUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await dungSaiComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last DungSai', async () => {
    const nbButtonsBeforeDelete = await dungSaiComponentsPage.countDeleteButtons();
    await dungSaiComponentsPage.clickOnLastDeleteButton();

    dungSaiDeleteDialog = new DungSaiDeleteDialog();
    expect(await dungSaiDeleteDialog.getDialogTitle()).to.eq('ctApp.dungSai.delete.question');
    await dungSaiDeleteDialog.clickOnConfirmButton();

    expect(await dungSaiComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
