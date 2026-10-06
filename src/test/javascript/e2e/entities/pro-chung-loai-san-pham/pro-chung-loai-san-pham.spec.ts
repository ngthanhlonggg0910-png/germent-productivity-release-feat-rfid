import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  ProChungLoaiSanPhamComponentsPage,
  ProChungLoaiSanPhamDeleteDialog,
  ProChungLoaiSanPhamUpdatePage
} from './pro-chung-loai-san-pham.page-object';

const expect = chai.expect;

describe('ProChungLoaiSanPham e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let proChungLoaiSanPhamComponentsPage: ProChungLoaiSanPhamComponentsPage;
  let proChungLoaiSanPhamUpdatePage: ProChungLoaiSanPhamUpdatePage;
  let proChungLoaiSanPhamDeleteDialog: ProChungLoaiSanPhamDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProChungLoaiSanPhams', async () => {
    await navBarPage.goToEntity('pro-chung-loai-san-pham');
    proChungLoaiSanPhamComponentsPage = new ProChungLoaiSanPhamComponentsPage();
    await browser.wait(ec.visibilityOf(proChungLoaiSanPhamComponentsPage.title), 5000);
    expect(await proChungLoaiSanPhamComponentsPage.getTitle()).to.eq('ctApp.proChungLoaiSanPham.home.title');
  });

  it('should load create ProChungLoaiSanPham page', async () => {
    await proChungLoaiSanPhamComponentsPage.clickOnCreateButton();
    proChungLoaiSanPhamUpdatePage = new ProChungLoaiSanPhamUpdatePage();
    expect(await proChungLoaiSanPhamUpdatePage.getPageTitle()).to.eq('ctApp.proChungLoaiSanPham.home.createOrEditLabel');
    await proChungLoaiSanPhamUpdatePage.cancel();
  });

  it('should create and save ProChungLoaiSanPhams', async () => {
    const nbButtonsBeforeCreate = await proChungLoaiSanPhamComponentsPage.countDeleteButtons();

    await proChungLoaiSanPhamComponentsPage.clickOnCreateButton();
    await promise.all([proChungLoaiSanPhamUpdatePage.setTenChungLoaiInput('tenChungLoai')]);
    expect(await proChungLoaiSanPhamUpdatePage.getTenChungLoaiInput()).to.eq(
      'tenChungLoai',
      'Expected TenChungLoai value to be equals to tenChungLoai'
    );
    await proChungLoaiSanPhamUpdatePage.save();
    expect(await proChungLoaiSanPhamUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await proChungLoaiSanPhamComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last ProChungLoaiSanPham', async () => {
    const nbButtonsBeforeDelete = await proChungLoaiSanPhamComponentsPage.countDeleteButtons();
    await proChungLoaiSanPhamComponentsPage.clickOnLastDeleteButton();

    proChungLoaiSanPhamDeleteDialog = new ProChungLoaiSanPhamDeleteDialog();
    expect(await proChungLoaiSanPhamDeleteDialog.getDialogTitle()).to.eq('ctApp.proChungLoaiSanPham.delete.question');
    await proChungLoaiSanPhamDeleteDialog.clickOnConfirmButton();

    expect(await proChungLoaiSanPhamComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
