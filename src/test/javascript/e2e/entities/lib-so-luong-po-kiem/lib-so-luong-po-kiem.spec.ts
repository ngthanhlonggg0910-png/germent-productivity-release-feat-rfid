import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  LibSoLuongPoKiemComponentsPage,
  LibSoLuongPoKiemDeleteDialog,
  LibSoLuongPoKiemUpdatePage
} from './lib-so-luong-po-kiem.page-object';

const expect = chai.expect;

describe('LibSoLuongPoKiem e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let libSoLuongPoKiemComponentsPage: LibSoLuongPoKiemComponentsPage;
  let libSoLuongPoKiemUpdatePage: LibSoLuongPoKiemUpdatePage;
  let libSoLuongPoKiemDeleteDialog: LibSoLuongPoKiemDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load LibSoLuongPoKiems', async () => {
    await navBarPage.goToEntity('lib-so-luong-po-kiem');
    libSoLuongPoKiemComponentsPage = new LibSoLuongPoKiemComponentsPage();
    await browser.wait(ec.visibilityOf(libSoLuongPoKiemComponentsPage.title), 5000);
    expect(await libSoLuongPoKiemComponentsPage.getTitle()).to.eq('ctApp.libSoLuongPoKiem.home.title');
  });

  it('should load create LibSoLuongPoKiem page', async () => {
    await libSoLuongPoKiemComponentsPage.clickOnCreateButton();
    libSoLuongPoKiemUpdatePage = new LibSoLuongPoKiemUpdatePage();
    expect(await libSoLuongPoKiemUpdatePage.getPageTitle()).to.eq('ctApp.libSoLuongPoKiem.home.createOrEditLabel');
    await libSoLuongPoKiemUpdatePage.cancel();
  });

  it('should create and save LibSoLuongPoKiems', async () => {
    const nbButtonsBeforeCreate = await libSoLuongPoKiemComponentsPage.countDeleteButtons();

    await libSoLuongPoKiemComponentsPage.clickOnCreateButton();
    await promise.all([libSoLuongPoKiemUpdatePage.setValueInput('5')]);
    expect(await libSoLuongPoKiemUpdatePage.getValueInput()).to.eq('5', 'Expected value value to be equals to 5');
    await libSoLuongPoKiemUpdatePage.save();
    expect(await libSoLuongPoKiemUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await libSoLuongPoKiemComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last LibSoLuongPoKiem', async () => {
    const nbButtonsBeforeDelete = await libSoLuongPoKiemComponentsPage.countDeleteButtons();
    await libSoLuongPoKiemComponentsPage.clickOnLastDeleteButton();

    libSoLuongPoKiemDeleteDialog = new LibSoLuongPoKiemDeleteDialog();
    expect(await libSoLuongPoKiemDeleteDialog.getDialogTitle()).to.eq('ctApp.libSoLuongPoKiem.delete.question');
    await libSoLuongPoKiemDeleteDialog.clickOnConfirmButton();

    expect(await libSoLuongPoKiemComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
