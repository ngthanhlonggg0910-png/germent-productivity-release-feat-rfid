import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProCumLoiComponentsPage, ProCumLoiDeleteDialog, ProCumLoiUpdatePage } from './pro-cum-loi.page-object';

const expect = chai.expect;

describe('ProCumLoi e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let proCumLoiComponentsPage: ProCumLoiComponentsPage;
  let proCumLoiUpdatePage: ProCumLoiUpdatePage;
  let proCumLoiDeleteDialog: ProCumLoiDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProCumLois', async () => {
    await navBarPage.goToEntity('pro-cum-loi');
    proCumLoiComponentsPage = new ProCumLoiComponentsPage();
    await browser.wait(ec.visibilityOf(proCumLoiComponentsPage.title), 5000);
    expect(await proCumLoiComponentsPage.getTitle()).to.eq('ctApp.proCumLoi.home.title');
  });

  it('should load create ProCumLoi page', async () => {
    await proCumLoiComponentsPage.clickOnCreateButton();
    proCumLoiUpdatePage = new ProCumLoiUpdatePage();
    expect(await proCumLoiUpdatePage.getPageTitle()).to.eq('ctApp.proCumLoi.home.createOrEditLabel');
    await proCumLoiUpdatePage.cancel();
  });

  it('should create and save ProCumLois', async () => {
    const nbButtonsBeforeCreate = await proCumLoiComponentsPage.countDeleteButtons();

    await proCumLoiComponentsPage.clickOnCreateButton();
    await promise.all([proCumLoiUpdatePage.setTenInput('ten'), proCumLoiUpdatePage.setMauInput('mau')]);
    expect(await proCumLoiUpdatePage.getTenInput()).to.eq('ten', 'Expected Ten value to be equals to ten');
    expect(await proCumLoiUpdatePage.getMauInput()).to.eq('mau', 'Expected Mau value to be equals to mau');
    await proCumLoiUpdatePage.save();
    expect(await proCumLoiUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await proCumLoiComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ProCumLoi', async () => {
    const nbButtonsBeforeDelete = await proCumLoiComponentsPage.countDeleteButtons();
    await proCumLoiComponentsPage.clickOnLastDeleteButton();

    proCumLoiDeleteDialog = new ProCumLoiDeleteDialog();
    expect(await proCumLoiDeleteDialog.getDialogTitle()).to.eq('ctApp.proCumLoi.delete.question');
    await proCumLoiDeleteDialog.clickOnConfirmButton();

    expect(await proCumLoiComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
