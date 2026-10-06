import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ThongSoDoComponentsPage, ThongSoDoDeleteDialog, ThongSoDoUpdatePage } from './thong-so-do.page-object';

const expect = chai.expect;

describe('ThongSoDo e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let thongSoDoComponentsPage: ThongSoDoComponentsPage;
  let thongSoDoUpdatePage: ThongSoDoUpdatePage;
  let thongSoDoDeleteDialog: ThongSoDoDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ThongSoDos', async () => {
    await navBarPage.goToEntity('thong-so-do');
    thongSoDoComponentsPage = new ThongSoDoComponentsPage();
    await browser.wait(ec.visibilityOf(thongSoDoComponentsPage.title), 5000);
    expect(await thongSoDoComponentsPage.getTitle()).to.eq('ctApp.thongSoDo.home.title');
  });

  it('should load create ThongSoDo page', async () => {
    await thongSoDoComponentsPage.clickOnCreateButton();
    thongSoDoUpdatePage = new ThongSoDoUpdatePage();
    expect(await thongSoDoUpdatePage.getPageTitle()).to.eq('ctApp.thongSoDo.home.createOrEditLabel');
    await thongSoDoUpdatePage.cancel();
  });

  it('should create and save ThongSoDos', async () => {
    const nbButtonsBeforeCreate = await thongSoDoComponentsPage.countDeleteButtons();

    await thongSoDoComponentsPage.clickOnCreateButton();
    await promise.all([
      thongSoDoUpdatePage.setCodeInput('code'),
      thongSoDoUpdatePage.setTenInput('ten'),
      thongSoDoUpdatePage.dungSaiSelectLastOption()
    ]);
    expect(await thongSoDoUpdatePage.getCodeInput()).to.eq('code', 'Expected Code value to be equals to code');
    expect(await thongSoDoUpdatePage.getTenInput()).to.eq('ten', 'Expected Ten value to be equals to ten');
    await thongSoDoUpdatePage.save();
    expect(await thongSoDoUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await thongSoDoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ThongSoDo', async () => {
    const nbButtonsBeforeDelete = await thongSoDoComponentsPage.countDeleteButtons();
    await thongSoDoComponentsPage.clickOnLastDeleteButton();

    thongSoDoDeleteDialog = new ThongSoDoDeleteDialog();
    expect(await thongSoDoDeleteDialog.getDialogTitle()).to.eq('ctApp.thongSoDo.delete.question');
    await thongSoDoDeleteDialog.clickOnConfirmButton();

    expect(await thongSoDoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
