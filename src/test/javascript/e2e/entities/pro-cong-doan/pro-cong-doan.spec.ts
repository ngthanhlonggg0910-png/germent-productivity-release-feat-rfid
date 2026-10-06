import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProCongDoanComponentsPage, ProCongDoanDeleteDialog, ProCongDoanUpdatePage } from './pro-cong-doan.page-object';

const expect = chai.expect;

describe('ProCongDoan e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let proCongDoanComponentsPage: ProCongDoanComponentsPage;
  let proCongDoanUpdatePage: ProCongDoanUpdatePage;
  let proCongDoanDeleteDialog: ProCongDoanDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProCongDoans', async () => {
    await navBarPage.goToEntity('pro-cong-doan');
    proCongDoanComponentsPage = new ProCongDoanComponentsPage();
    await browser.wait(ec.visibilityOf(proCongDoanComponentsPage.title), 5000);
    expect(await proCongDoanComponentsPage.getTitle()).to.eq('ctApp.proCongDoan.home.title');
  });

  it('should load create ProCongDoan page', async () => {
    await proCongDoanComponentsPage.clickOnCreateButton();
    proCongDoanUpdatePage = new ProCongDoanUpdatePage();
    expect(await proCongDoanUpdatePage.getPageTitle()).to.eq('ctApp.proCongDoan.home.createOrEditLabel');
    await proCongDoanUpdatePage.cancel();
  });

  it('should create and save ProCongDoans', async () => {
    const nbButtonsBeforeCreate = await proCongDoanComponentsPage.countDeleteButtons();

    await proCongDoanComponentsPage.clickOnCreateButton();
    await promise.all([
      proCongDoanUpdatePage.setTenInput('ten'),
      proCongDoanUpdatePage.setNhomCongDoanInput('nhomCongDoan'),
      proCongDoanUpdatePage.proNhomCongDoanSelectLastOption()
    ]);
    expect(await proCongDoanUpdatePage.getTenInput()).to.eq('ten', 'Expected Ten value to be equals to ten');
    expect(await proCongDoanUpdatePage.getNhomCongDoanInput()).to.eq(
      'nhomCongDoan',
      'Expected NhomCongDoan value to be equals to nhomCongDoan'
    );
    await proCongDoanUpdatePage.save();
    expect(await proCongDoanUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await proCongDoanComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ProCongDoan', async () => {
    const nbButtonsBeforeDelete = await proCongDoanComponentsPage.countDeleteButtons();
    await proCongDoanComponentsPage.clickOnLastDeleteButton();

    proCongDoanDeleteDialog = new ProCongDoanDeleteDialog();
    expect(await proCongDoanDeleteDialog.getDialogTitle()).to.eq('ctApp.proCongDoan.delete.question');
    await proCongDoanDeleteDialog.clickOnConfirmButton();

    expect(await proCongDoanComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
