import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SysGroupModuleComponentsPage, SysGroupModuleDeleteDialog, SysGroupModuleUpdatePage } from './sys-group-module.page-object';

const expect = chai.expect;

describe('SysGroupModule e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let sysGroupModuleComponentsPage: SysGroupModuleComponentsPage;
  let sysGroupModuleUpdatePage: SysGroupModuleUpdatePage;
  let sysGroupModuleDeleteDialog: SysGroupModuleDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load SysGroupModules', async () => {
    await navBarPage.goToEntity('sys-group-module');
    sysGroupModuleComponentsPage = new SysGroupModuleComponentsPage();
    await browser.wait(ec.visibilityOf(sysGroupModuleComponentsPage.title), 5000);
    expect(await sysGroupModuleComponentsPage.getTitle()).to.eq('ctApp.sysGroupModule.home.title');
  });

  it('should load create SysGroupModule page', async () => {
    await sysGroupModuleComponentsPage.clickOnCreateButton();
    sysGroupModuleUpdatePage = new SysGroupModuleUpdatePage();
    expect(await sysGroupModuleUpdatePage.getPageTitle()).to.eq('ctApp.sysGroupModule.home.createOrEditLabel');
    await sysGroupModuleUpdatePage.cancel();
  });

  it('should create and save SysGroupModules', async () => {
    const nbButtonsBeforeCreate = await sysGroupModuleComponentsPage.countDeleteButtons();

    await sysGroupModuleComponentsPage.clickOnCreateButton();
    await promise.all([
      sysGroupModuleUpdatePage.setNameInput('name'),
      sysGroupModuleUpdatePage.setCaptionInput('caption'),
      sysGroupModuleUpdatePage.setPrimaryImageUriInput('primaryImageUri'),
      sysGroupModuleUpdatePage.setSecondaryImageUriInput('secondaryImageUri')
    ]);
    expect(await sysGroupModuleUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await sysGroupModuleUpdatePage.getCaptionInput()).to.eq('caption', 'Expected Caption value to be equals to caption');
    expect(await sysGroupModuleUpdatePage.getPrimaryImageUriInput()).to.eq(
      'primaryImageUri',
      'Expected PrimaryImageUri value to be equals to primaryImageUri'
    );
    expect(await sysGroupModuleUpdatePage.getSecondaryImageUriInput()).to.eq(
      'secondaryImageUri',
      'Expected SecondaryImageUri value to be equals to secondaryImageUri'
    );
    await sysGroupModuleUpdatePage.save();
    expect(await sysGroupModuleUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await sysGroupModuleComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last SysGroupModule', async () => {
    const nbButtonsBeforeDelete = await sysGroupModuleComponentsPage.countDeleteButtons();
    await sysGroupModuleComponentsPage.clickOnLastDeleteButton();

    sysGroupModuleDeleteDialog = new SysGroupModuleDeleteDialog();
    expect(await sysGroupModuleDeleteDialog.getDialogTitle()).to.eq('ctApp.sysGroupModule.delete.question');
    await sysGroupModuleDeleteDialog.clickOnConfirmButton();

    expect(await sysGroupModuleComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
