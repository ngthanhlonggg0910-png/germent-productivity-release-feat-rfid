import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProRefinalComponentsPage, ProRefinalDeleteDialog, ProRefinalUpdatePage } from './pro-refinal.page-object';

const expect = chai.expect;

describe('ProRefinal e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let proRefinalComponentsPage: ProRefinalComponentsPage;
  let proRefinalUpdatePage: ProRefinalUpdatePage;
  let proRefinalDeleteDialog: ProRefinalDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProRefinals', async () => {
    await navBarPage.goToEntity('pro-refinal');
    proRefinalComponentsPage = new ProRefinalComponentsPage();
    await browser.wait(ec.visibilityOf(proRefinalComponentsPage.title), 5000);
    expect(await proRefinalComponentsPage.getTitle()).to.eq('ctApp.proRefinal.home.title');
  });

  it('should load create ProRefinal page', async () => {
    await proRefinalComponentsPage.clickOnCreateButton();
    proRefinalUpdatePage = new ProRefinalUpdatePage();
    expect(await proRefinalUpdatePage.getPageTitle()).to.eq('ctApp.proRefinal.home.createOrEditLabel');
    await proRefinalUpdatePage.cancel();
  });

  it('should create and save ProRefinals', async () => {
    const nbButtonsBeforeCreate = await proRefinalComponentsPage.countDeleteButtons();

    await proRefinalComponentsPage.clickOnCreateButton();
    await promise.all([
      proRefinalUpdatePage.setProKHSXIdInput('5'),
      proRefinalUpdatePage.setCheckPointIDInput('5'),
      proRefinalUpdatePage.setProNSCLIdInput('5'),
      proRefinalUpdatePage.setNgayGioKiemInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      proRefinalUpdatePage.setSoLuongKiemInput('5'),
      proRefinalUpdatePage.setHangDatHienTaiInput('5'),
      proRefinalUpdatePage.setDoAmInput('5')
    ]);
    expect(await proRefinalUpdatePage.getProKHSXIdInput()).to.eq('5', 'Expected proKHSXId value to be equals to 5');
    expect(await proRefinalUpdatePage.getCheckPointIDInput()).to.eq('5', 'Expected checkPointID value to be equals to 5');
    expect(await proRefinalUpdatePage.getProNSCLIdInput()).to.eq('5', 'Expected proNSCLId value to be equals to 5');
    expect(await proRefinalUpdatePage.getNgayGioKiemInput()).to.contain(
      '2001-01-01T02:30',
      'Expected ngayGioKiem value to be equals to 2000-12-31'
    );
    expect(await proRefinalUpdatePage.getSoLuongKiemInput()).to.eq('5', 'Expected soLuongKiem value to be equals to 5');
    expect(await proRefinalUpdatePage.getHangDatHienTaiInput()).to.eq('5', 'Expected hangDatHienTai value to be equals to 5');
    expect(await proRefinalUpdatePage.getDoAmInput()).to.eq('5', 'Expected doAm value to be equals to 5');
    await proRefinalUpdatePage.save();
    expect(await proRefinalUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await proRefinalComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ProRefinal', async () => {
    const nbButtonsBeforeDelete = await proRefinalComponentsPage.countDeleteButtons();
    await proRefinalComponentsPage.clickOnLastDeleteButton();

    proRefinalDeleteDialog = new ProRefinalDeleteDialog();
    expect(await proRefinalDeleteDialog.getDialogTitle()).to.eq('ctApp.proRefinal.delete.question');
    await proRefinalDeleteDialog.clickOnConfirmButton();

    expect(await proRefinalComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
