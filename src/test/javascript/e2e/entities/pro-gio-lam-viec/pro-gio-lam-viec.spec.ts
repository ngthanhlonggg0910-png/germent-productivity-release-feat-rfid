import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProGioLamViecComponentsPage, ProGioLamViecDeleteDialog, ProGioLamViecUpdatePage } from './pro-gio-lam-viec.page-object';

const expect = chai.expect;

describe('ProGioLamViec e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let proGioLamViecComponentsPage: ProGioLamViecComponentsPage;
  let proGioLamViecUpdatePage: ProGioLamViecUpdatePage;
  let proGioLamViecDeleteDialog: ProGioLamViecDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProGioLamViecs', async () => {
    await navBarPage.goToEntity('pro-gio-lam-viec');
    proGioLamViecComponentsPage = new ProGioLamViecComponentsPage();
    await browser.wait(ec.visibilityOf(proGioLamViecComponentsPage.title), 5000);
    expect(await proGioLamViecComponentsPage.getTitle()).to.eq('ctApp.proGioLamViec.home.title');
  });

  it('should load create ProGioLamViec page', async () => {
    await proGioLamViecComponentsPage.clickOnCreateButton();
    proGioLamViecUpdatePage = new ProGioLamViecUpdatePage();
    expect(await proGioLamViecUpdatePage.getPageTitle()).to.eq('ctApp.proGioLamViec.home.createOrEditLabel');
    await proGioLamViecUpdatePage.cancel();
  });

  it('should create and save ProGioLamViecs', async () => {
    const nbButtonsBeforeCreate = await proGioLamViecComponentsPage.countDeleteButtons();

    await proGioLamViecComponentsPage.clickOnCreateButton();
    await promise.all([
      proGioLamViecUpdatePage.setGioBatDauInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      proGioLamViecUpdatePage.setGioKetThucInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      proGioLamViecUpdatePage.gioLamSelectLastOption(),
      proGioLamViecUpdatePage.setThoiGianInput('5'),
      proGioLamViecUpdatePage.setCaptionTVInput('captionTV')
    ]);
    expect(await proGioLamViecUpdatePage.getGioBatDauInput()).to.contain(
      '2001-01-01T02:30',
      'Expected gioBatDau value to be equals to 2000-12-31'
    );
    expect(await proGioLamViecUpdatePage.getGioKetThucInput()).to.contain(
      '2001-01-01T02:30',
      'Expected gioKetThuc value to be equals to 2000-12-31'
    );
    expect(await proGioLamViecUpdatePage.getThoiGianInput()).to.eq('5', 'Expected thoiGian value to be equals to 5');
    expect(await proGioLamViecUpdatePage.getCaptionTVInput()).to.eq('captionTV', 'Expected CaptionTV value to be equals to captionTV');
    await proGioLamViecUpdatePage.save();
    expect(await proGioLamViecUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await proGioLamViecComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ProGioLamViec', async () => {
    const nbButtonsBeforeDelete = await proGioLamViecComponentsPage.countDeleteButtons();
    await proGioLamViecComponentsPage.clickOnLastDeleteButton();

    proGioLamViecDeleteDialog = new ProGioLamViecDeleteDialog();
    expect(await proGioLamViecDeleteDialog.getDialogTitle()).to.eq('ctApp.proGioLamViec.delete.question');
    await proGioLamViecDeleteDialog.clickOnConfirmButton();

    expect(await proGioLamViecComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
