import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ThongSoDoSPSizeComponentsPage, ThongSoDoSPSizeDeleteDialog, ThongSoDoSPSizeUpdatePage } from './thong-so-do-sp-size.page-object';

const expect = chai.expect;

describe('ThongSoDoSPSize e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let thongSoDoSPSizeComponentsPage: ThongSoDoSPSizeComponentsPage;
  let thongSoDoSPSizeUpdatePage: ThongSoDoSPSizeUpdatePage;
  let thongSoDoSPSizeDeleteDialog: ThongSoDoSPSizeDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ThongSoDoSPSizes', async () => {
    await navBarPage.goToEntity('thong-so-do-sp-size');
    thongSoDoSPSizeComponentsPage = new ThongSoDoSPSizeComponentsPage();
    await browser.wait(ec.visibilityOf(thongSoDoSPSizeComponentsPage.title), 5000);
    expect(await thongSoDoSPSizeComponentsPage.getTitle()).to.eq('ctApp.thongSoDoSPSize.home.title');
  });

  it('should load create ThongSoDoSPSize page', async () => {
    await thongSoDoSPSizeComponentsPage.clickOnCreateButton();
    thongSoDoSPSizeUpdatePage = new ThongSoDoSPSizeUpdatePage();
    expect(await thongSoDoSPSizeUpdatePage.getPageTitle()).to.eq('ctApp.thongSoDoSPSize.home.createOrEditLabel');
    await thongSoDoSPSizeUpdatePage.cancel();
  });

  it('should create and save ThongSoDoSPSizes', async () => {
    const nbButtonsBeforeCreate = await thongSoDoSPSizeComponentsPage.countDeleteButtons();

    await thongSoDoSPSizeComponentsPage.clickOnCreateButton();
    await promise.all([
      thongSoDoSPSizeUpdatePage.setSizeInput('size'),
      thongSoDoSPSizeUpdatePage.setCmThongSoChuanInput('cmThongSoChuan'),
      thongSoDoSPSizeUpdatePage.setInchThongSoChuanInput('inchThongSoChuan'),
      thongSoDoSPSizeUpdatePage.setCmThongSoChuanValueInput('5'),
      thongSoDoSPSizeUpdatePage.setInchThongSoChuanValueInput('5'),
      thongSoDoSPSizeUpdatePage.thongSoDoSPSelectLastOption()
    ]);
    expect(await thongSoDoSPSizeUpdatePage.getSizeInput()).to.eq('size', 'Expected Size value to be equals to size');
    expect(await thongSoDoSPSizeUpdatePage.getCmThongSoChuanInput()).to.eq(
      'cmThongSoChuan',
      'Expected CmThongSoChuan value to be equals to cmThongSoChuan'
    );
    expect(await thongSoDoSPSizeUpdatePage.getInchThongSoChuanInput()).to.eq(
      'inchThongSoChuan',
      'Expected InchThongSoChuan value to be equals to inchThongSoChuan'
    );
    expect(await thongSoDoSPSizeUpdatePage.getCmThongSoChuanValueInput()).to.eq(
      '5',
      'Expected cmThongSoChuanValue value to be equals to 5'
    );
    expect(await thongSoDoSPSizeUpdatePage.getInchThongSoChuanValueInput()).to.eq(
      '5',
      'Expected inchThongSoChuanValue value to be equals to 5'
    );
    await thongSoDoSPSizeUpdatePage.save();
    expect(await thongSoDoSPSizeUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await thongSoDoSPSizeComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last ThongSoDoSPSize', async () => {
    const nbButtonsBeforeDelete = await thongSoDoSPSizeComponentsPage.countDeleteButtons();
    await thongSoDoSPSizeComponentsPage.clickOnLastDeleteButton();

    thongSoDoSPSizeDeleteDialog = new ThongSoDoSPSizeDeleteDialog();
    expect(await thongSoDoSPSizeDeleteDialog.getDialogTitle()).to.eq('ctApp.thongSoDoSPSize.delete.question');
    await thongSoDoSPSizeDeleteDialog.clickOnConfirmButton();

    expect(await thongSoDoSPSizeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
