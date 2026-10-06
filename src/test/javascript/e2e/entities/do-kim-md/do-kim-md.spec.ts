import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { DoKimMdComponentsPage, DoKimMdDeleteDialog, DoKimMdUpdatePage } from './do-kim-md.page-object';

const expect = chai.expect;

describe('DoKimMd e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let doKimMdComponentsPage: DoKimMdComponentsPage;
  let doKimMdUpdatePage: DoKimMdUpdatePage;
  let doKimMdDeleteDialog: DoKimMdDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load DoKimMds', async () => {
    await navBarPage.goToEntity('do-kim-md');
    doKimMdComponentsPage = new DoKimMdComponentsPage();
    await browser.wait(ec.visibilityOf(doKimMdComponentsPage.title), 5000);
    expect(await doKimMdComponentsPage.getTitle()).to.eq('ctApp.doKimMd.home.title');
  });

  it('should load create DoKimMd page', async () => {
    await doKimMdComponentsPage.clickOnCreateButton();
    doKimMdUpdatePage = new DoKimMdUpdatePage();
    expect(await doKimMdUpdatePage.getPageTitle()).to.eq('ctApp.doKimMd.home.createOrEditLabel');
    await doKimMdUpdatePage.cancel();
  });

  it('should create and save DoKimMds', async () => {
    const nbButtonsBeforeCreate = await doKimMdComponentsPage.countDeleteButtons();

    await doKimMdComponentsPage.clickOnCreateButton();
    await promise.all([doKimMdUpdatePage.setValueInput('5'), doKimMdUpdatePage.setDisplayInput('display')]);
    expect(await doKimMdUpdatePage.getValueInput()).to.eq('5', 'Expected value value to be equals to 5');
    expect(await doKimMdUpdatePage.getDisplayInput()).to.eq('display', 'Expected Display value to be equals to display');
    await doKimMdUpdatePage.save();
    expect(await doKimMdUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await doKimMdComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last DoKimMd', async () => {
    const nbButtonsBeforeDelete = await doKimMdComponentsPage.countDeleteButtons();
    await doKimMdComponentsPage.clickOnLastDeleteButton();

    doKimMdDeleteDialog = new DoKimMdDeleteDialog();
    expect(await doKimMdDeleteDialog.getDialogTitle()).to.eq('ctApp.doKimMd.delete.question');
    await doKimMdDeleteDialog.clickOnConfirmButton();

    expect(await doKimMdComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
