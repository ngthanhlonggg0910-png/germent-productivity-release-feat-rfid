import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SysFileComponentsPage, SysFileDeleteDialog, SysFileUpdatePage } from './sys-file.page-object';

const expect = chai.expect;

describe('SysFile e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let sysFileComponentsPage: SysFileComponentsPage;
  let sysFileUpdatePage: SysFileUpdatePage;
  let sysFileDeleteDialog: SysFileDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load SysFiles', async () => {
    await navBarPage.goToEntity('sys-file');
    sysFileComponentsPage = new SysFileComponentsPage();
    await browser.wait(ec.visibilityOf(sysFileComponentsPage.title), 5000);
    expect(await sysFileComponentsPage.getTitle()).to.eq('ctApp.sysFile.home.title');
  });

  it('should load create SysFile page', async () => {
    await sysFileComponentsPage.clickOnCreateButton();
    sysFileUpdatePage = new SysFileUpdatePage();
    expect(await sysFileUpdatePage.getPageTitle()).to.eq('ctApp.sysFile.home.createOrEditLabel');
    await sysFileUpdatePage.cancel();
  });

  it('should create and save SysFiles', async () => {
    const nbButtonsBeforeCreate = await sysFileComponentsPage.countDeleteButtons();

    await sysFileComponentsPage.clickOnCreateButton();
    await promise.all([
      sysFileUpdatePage.setFileNameInput('fileName'),
      sysFileUpdatePage.setFileUriInput('fileUri'),
      sysFileUpdatePage.setFileExtensionInput('fileExtension'),
      sysFileUpdatePage.setSizeInput('5'),
      sysFileUpdatePage.setUploadedAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM')
    ]);
    expect(await sysFileUpdatePage.getFileNameInput()).to.eq('fileName', 'Expected FileName value to be equals to fileName');
    expect(await sysFileUpdatePage.getFileUriInput()).to.eq('fileUri', 'Expected FileUri value to be equals to fileUri');
    expect(await sysFileUpdatePage.getFileExtensionInput()).to.eq(
      'fileExtension',
      'Expected FileExtension value to be equals to fileExtension'
    );
    expect(await sysFileUpdatePage.getSizeInput()).to.eq('5', 'Expected size value to be equals to 5');
    expect(await sysFileUpdatePage.getUploadedAtInput()).to.contain(
      '2001-01-01T02:30',
      'Expected uploadedAt value to be equals to 2000-12-31'
    );
    await sysFileUpdatePage.save();
    expect(await sysFileUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await sysFileComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last SysFile', async () => {
    const nbButtonsBeforeDelete = await sysFileComponentsPage.countDeleteButtons();
    await sysFileComponentsPage.clickOnLastDeleteButton();

    sysFileDeleteDialog = new SysFileDeleteDialog();
    expect(await sysFileDeleteDialog.getDialogTitle()).to.eq('ctApp.sysFile.delete.question');
    await sysFileDeleteDialog.clickOnConfirmButton();

    expect(await sysFileComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
