import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { DoAmComponentsPage, DoAmDeleteDialog, DoAmUpdatePage } from './do-am.page-object';

const expect = chai.expect;

describe('DoAm e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let doAmComponentsPage: DoAmComponentsPage;
  let doAmUpdatePage: DoAmUpdatePage;
  let doAmDeleteDialog: DoAmDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load DoAms', async () => {
    await navBarPage.goToEntity('do-am');
    doAmComponentsPage = new DoAmComponentsPage();
    await browser.wait(ec.visibilityOf(doAmComponentsPage.title), 5000);
    expect(await doAmComponentsPage.getTitle()).to.eq('ctApp.doAm.home.title');
  });

  it('should load create DoAm page', async () => {
    await doAmComponentsPage.clickOnCreateButton();
    doAmUpdatePage = new DoAmUpdatePage();
    expect(await doAmUpdatePage.getPageTitle()).to.eq('ctApp.doAm.home.createOrEditLabel');
    await doAmUpdatePage.cancel();
  });

  it('should create and save DoAms', async () => {
    const nbButtonsBeforeCreate = await doAmComponentsPage.countDeleteButtons();

    await doAmComponentsPage.clickOnCreateButton();
    await promise.all([doAmUpdatePage.setValueInput('5'), doAmUpdatePage.setDisplayInput('display')]);
    expect(await doAmUpdatePage.getValueInput()).to.eq('5', 'Expected value value to be equals to 5');
    expect(await doAmUpdatePage.getDisplayInput()).to.eq('display', 'Expected Display value to be equals to display');
    await doAmUpdatePage.save();
    expect(await doAmUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await doAmComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last DoAm', async () => {
    const nbButtonsBeforeDelete = await doAmComponentsPage.countDeleteButtons();
    await doAmComponentsPage.clickOnLastDeleteButton();

    doAmDeleteDialog = new DoAmDeleteDialog();
    expect(await doAmDeleteDialog.getDialogTitle()).to.eq('ctApp.doAm.delete.question');
    await doAmDeleteDialog.clickOnConfirmButton();

    expect(await doAmComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
