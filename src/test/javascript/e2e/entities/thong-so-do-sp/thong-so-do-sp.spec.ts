import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ThongSoDoSPComponentsPage, ThongSoDoSPDeleteDialog, ThongSoDoSPUpdatePage } from './thong-so-do-sp.page-object';

const expect = chai.expect;

describe('ThongSoDoSP e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let thongSoDoSPComponentsPage: ThongSoDoSPComponentsPage;
  let thongSoDoSPUpdatePage: ThongSoDoSPUpdatePage;
  let thongSoDoSPDeleteDialog: ThongSoDoSPDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ThongSoDoSPS', async () => {
    await navBarPage.goToEntity('thong-so-do-sp');
    thongSoDoSPComponentsPage = new ThongSoDoSPComponentsPage();
    await browser.wait(ec.visibilityOf(thongSoDoSPComponentsPage.title), 5000);
    expect(await thongSoDoSPComponentsPage.getTitle()).to.eq('ctApp.thongSoDoSP.home.title');
  });

  it('should load create ThongSoDoSP page', async () => {
    await thongSoDoSPComponentsPage.clickOnCreateButton();
    thongSoDoSPUpdatePage = new ThongSoDoSPUpdatePage();
    expect(await thongSoDoSPUpdatePage.getPageTitle()).to.eq('ctApp.thongSoDoSP.home.createOrEditLabel');
    await thongSoDoSPUpdatePage.cancel();
  });

  it('should create and save ThongSoDoSPS', async () => {
    const nbButtonsBeforeCreate = await thongSoDoSPComponentsPage.countDeleteButtons();

    await thongSoDoSPComponentsPage.clickOnCreateButton();
    await promise.all([
      thongSoDoSPUpdatePage.setNoteInput('note'),
      thongSoDoSPUpdatePage.thongSoDoSelectLastOption(),
      thongSoDoSPUpdatePage.dungSaiSelectLastOption(),
      thongSoDoSPUpdatePage.proSanPhamSelectLastOption()
    ]);
    expect(await thongSoDoSPUpdatePage.getNoteInput()).to.eq('note', 'Expected Note value to be equals to note');
    await thongSoDoSPUpdatePage.save();
    expect(await thongSoDoSPUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await thongSoDoSPComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ThongSoDoSP', async () => {
    const nbButtonsBeforeDelete = await thongSoDoSPComponentsPage.countDeleteButtons();
    await thongSoDoSPComponentsPage.clickOnLastDeleteButton();

    thongSoDoSPDeleteDialog = new ThongSoDoSPDeleteDialog();
    expect(await thongSoDoSPDeleteDialog.getDialogTitle()).to.eq('ctApp.thongSoDoSP.delete.question');
    await thongSoDoSPDeleteDialog.clickOnConfirmButton();

    expect(await thongSoDoSPComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
