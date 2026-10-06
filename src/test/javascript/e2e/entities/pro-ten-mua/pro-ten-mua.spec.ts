import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProTenMuaComponentsPage, ProTenMuaDeleteDialog, ProTenMuaUpdatePage } from './pro-ten-mua.page-object';

const expect = chai.expect;

describe('ProTenMua e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let proTenMuaComponentsPage: ProTenMuaComponentsPage;
  let proTenMuaUpdatePage: ProTenMuaUpdatePage;
  let proTenMuaDeleteDialog: ProTenMuaDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProTenMuas', async () => {
    await navBarPage.goToEntity('pro-ten-mua');
    proTenMuaComponentsPage = new ProTenMuaComponentsPage();
    await browser.wait(ec.visibilityOf(proTenMuaComponentsPage.title), 5000);
    expect(await proTenMuaComponentsPage.getTitle()).to.eq('ctApp.proTenMua.home.title');
  });

  it('should load create ProTenMua page', async () => {
    await proTenMuaComponentsPage.clickOnCreateButton();
    proTenMuaUpdatePage = new ProTenMuaUpdatePage();
    expect(await proTenMuaUpdatePage.getPageTitle()).to.eq('ctApp.proTenMua.home.createOrEditLabel');
    await proTenMuaUpdatePage.cancel();
  });

  it('should create and save ProTenMuas', async () => {
    const nbButtonsBeforeCreate = await proTenMuaComponentsPage.countDeleteButtons();

    await proTenMuaComponentsPage.clickOnCreateButton();
    await promise.all([proTenMuaUpdatePage.setTenMuaInput('tenMua')]);
    expect(await proTenMuaUpdatePage.getTenMuaInput()).to.eq('tenMua', 'Expected TenMua value to be equals to tenMua');
    await proTenMuaUpdatePage.save();
    expect(await proTenMuaUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await proTenMuaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ProTenMua', async () => {
    const nbButtonsBeforeDelete = await proTenMuaComponentsPage.countDeleteButtons();
    await proTenMuaComponentsPage.clickOnLastDeleteButton();

    proTenMuaDeleteDialog = new ProTenMuaDeleteDialog();
    expect(await proTenMuaDeleteDialog.getDialogTitle()).to.eq('ctApp.proTenMua.delete.question');
    await proTenMuaDeleteDialog.clickOnConfirmButton();

    expect(await proTenMuaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
