import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SysPermissionComponentsPage, SysPermissionDeleteDialog, SysPermissionUpdatePage } from './sys-permission.page-object';

const expect = chai.expect;

describe('SysPermission e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let sysPermissionComponentsPage: SysPermissionComponentsPage;
  let sysPermissionUpdatePage: SysPermissionUpdatePage;
  let sysPermissionDeleteDialog: SysPermissionDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load SysPermissions', async () => {
    await navBarPage.goToEntity('sys-permission');
    sysPermissionComponentsPage = new SysPermissionComponentsPage();
    await browser.wait(ec.visibilityOf(sysPermissionComponentsPage.title), 5000);
    expect(await sysPermissionComponentsPage.getTitle()).to.eq('ctApp.sysPermission.home.title');
  });

  it('should load create SysPermission page', async () => {
    await sysPermissionComponentsPage.clickOnCreateButton();
    sysPermissionUpdatePage = new SysPermissionUpdatePage();
    expect(await sysPermissionUpdatePage.getPageTitle()).to.eq('ctApp.sysPermission.home.createOrEditLabel');
    await sysPermissionUpdatePage.cancel();
  });

  it('should create and save SysPermissions', async () => {
    const nbButtonsBeforeCreate = await sysPermissionComponentsPage.countDeleteButtons();

    await sysPermissionComponentsPage.clickOnCreateButton();
    await promise.all([
      sysPermissionUpdatePage.setNameInput('name'),
      sysPermissionUpdatePage.setCaptionInput('caption'),
      sysPermissionUpdatePage.setLevelInput('5'),
      sysPermissionUpdatePage.setImageUriInput('imageUri'),
      sysPermissionUpdatePage.setEventNameInput('eventName'),
      sysPermissionUpdatePage.setHotkeyInput('hotkey')
    ]);
    expect(await sysPermissionUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await sysPermissionUpdatePage.getCaptionInput()).to.eq('caption', 'Expected Caption value to be equals to caption');
    expect(await sysPermissionUpdatePage.getLevelInput()).to.eq('5', 'Expected level value to be equals to 5');
    expect(await sysPermissionUpdatePage.getImageUriInput()).to.eq('imageUri', 'Expected ImageUri value to be equals to imageUri');
    expect(await sysPermissionUpdatePage.getEventNameInput()).to.eq('eventName', 'Expected EventName value to be equals to eventName');
    expect(await sysPermissionUpdatePage.getHotkeyInput()).to.eq('hotkey', 'Expected Hotkey value to be equals to hotkey');
    await sysPermissionUpdatePage.save();
    expect(await sysPermissionUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await sysPermissionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last SysPermission', async () => {
    const nbButtonsBeforeDelete = await sysPermissionComponentsPage.countDeleteButtons();
    await sysPermissionComponentsPage.clickOnLastDeleteButton();

    sysPermissionDeleteDialog = new SysPermissionDeleteDialog();
    expect(await sysPermissionDeleteDialog.getDialogTitle()).to.eq('ctApp.sysPermission.delete.question');
    await sysPermissionDeleteDialog.clickOnConfirmButton();

    expect(await sysPermissionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
