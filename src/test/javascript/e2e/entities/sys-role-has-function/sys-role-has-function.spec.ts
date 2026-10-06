import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  SysRoleHasFunctionComponentsPage,
  SysRoleHasFunctionDeleteDialog,
  SysRoleHasFunctionUpdatePage
} from './sys-role-has-function.page-object';

const expect = chai.expect;

describe('SysRoleHasFunction e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let sysRoleHasFunctionComponentsPage: SysRoleHasFunctionComponentsPage;
  let sysRoleHasFunctionUpdatePage: SysRoleHasFunctionUpdatePage;
  let sysRoleHasFunctionDeleteDialog: SysRoleHasFunctionDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load SysRoleHasFunctions', async () => {
    await navBarPage.goToEntity('sys-role-has-function');
    sysRoleHasFunctionComponentsPage = new SysRoleHasFunctionComponentsPage();
    await browser.wait(ec.visibilityOf(sysRoleHasFunctionComponentsPage.title), 5000);
    expect(await sysRoleHasFunctionComponentsPage.getTitle()).to.eq('ctApp.sysRoleHasFunction.home.title');
  });

  it('should load create SysRoleHasFunction page', async () => {
    await sysRoleHasFunctionComponentsPage.clickOnCreateButton();
    sysRoleHasFunctionUpdatePage = new SysRoleHasFunctionUpdatePage();
    expect(await sysRoleHasFunctionUpdatePage.getPageTitle()).to.eq('ctApp.sysRoleHasFunction.home.createOrEditLabel');
    await sysRoleHasFunctionUpdatePage.cancel();
  });

  it('should create and save SysRoleHasFunctions', async () => {
    const nbButtonsBeforeCreate = await sysRoleHasFunctionComponentsPage.countDeleteButtons();

    await sysRoleHasFunctionComponentsPage.clickOnCreateButton();
    await promise.all([
      sysRoleHasFunctionUpdatePage.sysFunctionSelectLastOption(),
      sysRoleHasFunctionUpdatePage.authoritySelectLastOption()
    ]);
    const selectedActivated = sysRoleHasFunctionUpdatePage.getActivatedInput();
    if (await selectedActivated.isSelected()) {
      await sysRoleHasFunctionUpdatePage.getActivatedInput().click();
      expect(await sysRoleHasFunctionUpdatePage.getActivatedInput().isSelected(), 'Expected activated not to be selected').to.be.false;
    } else {
      await sysRoleHasFunctionUpdatePage.getActivatedInput().click();
      expect(await sysRoleHasFunctionUpdatePage.getActivatedInput().isSelected(), 'Expected activated to be selected').to.be.true;
    }
    await sysRoleHasFunctionUpdatePage.save();
    expect(await sysRoleHasFunctionUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await sysRoleHasFunctionComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last SysRoleHasFunction', async () => {
    const nbButtonsBeforeDelete = await sysRoleHasFunctionComponentsPage.countDeleteButtons();
    await sysRoleHasFunctionComponentsPage.clickOnLastDeleteButton();

    sysRoleHasFunctionDeleteDialog = new SysRoleHasFunctionDeleteDialog();
    expect(await sysRoleHasFunctionDeleteDialog.getDialogTitle()).to.eq('ctApp.sysRoleHasFunction.delete.question');
    await sysRoleHasFunctionDeleteDialog.clickOnConfirmButton();

    expect(await sysRoleHasFunctionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
