import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProSanPhamComponentsPage, ProSanPhamDeleteDialog, ProSanPhamUpdatePage } from './pro-san-pham.page-object';

const expect = chai.expect;

describe('ProSanPham e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let proSanPhamComponentsPage: ProSanPhamComponentsPage;
  let proSanPhamUpdatePage: ProSanPhamUpdatePage;
  let proSanPhamDeleteDialog: ProSanPhamDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProSanPhams', async () => {
    await navBarPage.goToEntity('pro-san-pham');
    proSanPhamComponentsPage = new ProSanPhamComponentsPage();
    await browser.wait(ec.visibilityOf(proSanPhamComponentsPage.title), 5000);
    expect(await proSanPhamComponentsPage.getTitle()).to.eq('ctApp.proSanPham.home.title');
  });

  it('should load create ProSanPham page', async () => {
    await proSanPhamComponentsPage.clickOnCreateButton();
    proSanPhamUpdatePage = new ProSanPhamUpdatePage();
    expect(await proSanPhamUpdatePage.getPageTitle()).to.eq('ctApp.proSanPham.home.createOrEditLabel');
    await proSanPhamUpdatePage.cancel();
  });

  it('should create and save ProSanPhams', async () => {
    const nbButtonsBeforeCreate = await proSanPhamComponentsPage.countDeleteButtons();

    await proSanPhamComponentsPage.clickOnCreateButton();
    await promise.all([
      proSanPhamUpdatePage.setMaSanPhamInput('maSanPham'),
      proSanPhamUpdatePage.setMaDonHangInput('maDonHang'),
      proSanPhamUpdatePage.setPoInput('po'),
      proSanPhamUpdatePage.setSoLuongSanXuatInput('5'),
      proSanPhamUpdatePage.setThongSoValue1Input('thongSoValue1'),
      proSanPhamUpdatePage.setThongSoCaption1Input('thongSoCaption1'),
      proSanPhamUpdatePage.setThongSoValue2Input('thongSoValue2'),
      proSanPhamUpdatePage.setThongSoCaption2Input('thongSoCaption2'),
      proSanPhamUpdatePage.setThongSoValue3Input('thongSoValue3'),
      proSanPhamUpdatePage.setThongSoCaption3Input('thongSoCaption3'),
      proSanPhamUpdatePage.setThongSoValue4Input('thongSoValue4'),
      proSanPhamUpdatePage.setThongSoCaption4Input('thongSoCaption4'),
      proSanPhamUpdatePage.setThongSoValue5Input('thongSoValue5'),
      proSanPhamUpdatePage.setThongSoCaption5Input('thongSoCaption5'),
      proSanPhamUpdatePage.setThongSoValue6Input('thongSoValue6'),
      proSanPhamUpdatePage.setThongSoCaption6Input('thongSoCaption6'),
      proSanPhamUpdatePage.setThongSoValue7Input('thongSoValue7'),
      proSanPhamUpdatePage.setThongSoCaption7Input('thongSoCaption7'),
      proSanPhamUpdatePage.setThongSoValue8Input('thongSoValue8'),
      proSanPhamUpdatePage.setThongSoCaption8Input('thongSoCaption8'),
      proSanPhamUpdatePage.setThongSoValue9Input('thongSoValue9'),
      proSanPhamUpdatePage.setThongSoCaption9Input('thongSoCaption9'),
      proSanPhamUpdatePage.setThongSoValue10Input('thongSoValue10'),
      proSanPhamUpdatePage.setThongSoCaption10Input('thongSoCaption10'),
      proSanPhamUpdatePage.setColorInput('color'),
      proSanPhamUpdatePage.setHinhMatTruocURLInput('hinhMatTruocURL'),
      proSanPhamUpdatePage.setHinhMatSauURLInput('hinhMatSauURL'),
      proSanPhamUpdatePage.proKhachHangSelectLastOption(),
      proSanPhamUpdatePage.proChungLoaiSanPhamSelectLastOption(),
      proSanPhamUpdatePage.proTenMuaSelectLastOption()
    ]);
    expect(await proSanPhamUpdatePage.getMaSanPhamInput()).to.eq('maSanPham', 'Expected MaSanPham value to be equals to maSanPham');
    expect(await proSanPhamUpdatePage.getMaDonHangInput()).to.eq('maDonHang', 'Expected MaDonHang value to be equals to maDonHang');
    expect(await proSanPhamUpdatePage.getPoInput()).to.eq('po', 'Expected Po value to be equals to po');
    expect(await proSanPhamUpdatePage.getSoLuongSanXuatInput()).to.eq('5', 'Expected soLuongSanXuat value to be equals to 5');
    expect(await proSanPhamUpdatePage.getThongSoValue1Input()).to.eq(
      'thongSoValue1',
      'Expected ThongSoValue1 value to be equals to thongSoValue1'
    );
    expect(await proSanPhamUpdatePage.getThongSoCaption1Input()).to.eq(
      'thongSoCaption1',
      'Expected ThongSoCaption1 value to be equals to thongSoCaption1'
    );
    const selectedThongSoCheck1 = proSanPhamUpdatePage.getThongSoCheck1Input();
    if (await selectedThongSoCheck1.isSelected()) {
      await proSanPhamUpdatePage.getThongSoCheck1Input().click();
      expect(await proSanPhamUpdatePage.getThongSoCheck1Input().isSelected(), 'Expected thongSoCheck1 not to be selected').to.be.false;
    } else {
      await proSanPhamUpdatePage.getThongSoCheck1Input().click();
      expect(await proSanPhamUpdatePage.getThongSoCheck1Input().isSelected(), 'Expected thongSoCheck1 to be selected').to.be.true;
    }
    expect(await proSanPhamUpdatePage.getThongSoValue2Input()).to.eq(
      'thongSoValue2',
      'Expected ThongSoValue2 value to be equals to thongSoValue2'
    );
    expect(await proSanPhamUpdatePage.getThongSoCaption2Input()).to.eq(
      'thongSoCaption2',
      'Expected ThongSoCaption2 value to be equals to thongSoCaption2'
    );
    const selectedThongSoCheck2 = proSanPhamUpdatePage.getThongSoCheck2Input();
    if (await selectedThongSoCheck2.isSelected()) {
      await proSanPhamUpdatePage.getThongSoCheck2Input().click();
      expect(await proSanPhamUpdatePage.getThongSoCheck2Input().isSelected(), 'Expected thongSoCheck2 not to be selected').to.be.false;
    } else {
      await proSanPhamUpdatePage.getThongSoCheck2Input().click();
      expect(await proSanPhamUpdatePage.getThongSoCheck2Input().isSelected(), 'Expected thongSoCheck2 to be selected').to.be.true;
    }
    expect(await proSanPhamUpdatePage.getThongSoValue3Input()).to.eq(
      'thongSoValue3',
      'Expected ThongSoValue3 value to be equals to thongSoValue3'
    );
    expect(await proSanPhamUpdatePage.getThongSoCaption3Input()).to.eq(
      'thongSoCaption3',
      'Expected ThongSoCaption3 value to be equals to thongSoCaption3'
    );
    const selectedThongSoCheck3 = proSanPhamUpdatePage.getThongSoCheck3Input();
    if (await selectedThongSoCheck3.isSelected()) {
      await proSanPhamUpdatePage.getThongSoCheck3Input().click();
      expect(await proSanPhamUpdatePage.getThongSoCheck3Input().isSelected(), 'Expected thongSoCheck3 not to be selected').to.be.false;
    } else {
      await proSanPhamUpdatePage.getThongSoCheck3Input().click();
      expect(await proSanPhamUpdatePage.getThongSoCheck3Input().isSelected(), 'Expected thongSoCheck3 to be selected').to.be.true;
    }
    expect(await proSanPhamUpdatePage.getThongSoValue4Input()).to.eq(
      'thongSoValue4',
      'Expected ThongSoValue4 value to be equals to thongSoValue4'
    );
    expect(await proSanPhamUpdatePage.getThongSoCaption4Input()).to.eq(
      'thongSoCaption4',
      'Expected ThongSoCaption4 value to be equals to thongSoCaption4'
    );
    const selectedThongSoCheck4 = proSanPhamUpdatePage.getThongSoCheck4Input();
    if (await selectedThongSoCheck4.isSelected()) {
      await proSanPhamUpdatePage.getThongSoCheck4Input().click();
      expect(await proSanPhamUpdatePage.getThongSoCheck4Input().isSelected(), 'Expected thongSoCheck4 not to be selected').to.be.false;
    } else {
      await proSanPhamUpdatePage.getThongSoCheck4Input().click();
      expect(await proSanPhamUpdatePage.getThongSoCheck4Input().isSelected(), 'Expected thongSoCheck4 to be selected').to.be.true;
    }
    expect(await proSanPhamUpdatePage.getThongSoValue5Input()).to.eq(
      'thongSoValue5',
      'Expected ThongSoValue5 value to be equals to thongSoValue5'
    );
    expect(await proSanPhamUpdatePage.getThongSoCaption5Input()).to.eq(
      'thongSoCaption5',
      'Expected ThongSoCaption5 value to be equals to thongSoCaption5'
    );
    const selectedThongSoCheck5 = proSanPhamUpdatePage.getThongSoCheck5Input();
    if (await selectedThongSoCheck5.isSelected()) {
      await proSanPhamUpdatePage.getThongSoCheck5Input().click();
      expect(await proSanPhamUpdatePage.getThongSoCheck5Input().isSelected(), 'Expected thongSoCheck5 not to be selected').to.be.false;
    } else {
      await proSanPhamUpdatePage.getThongSoCheck5Input().click();
      expect(await proSanPhamUpdatePage.getThongSoCheck5Input().isSelected(), 'Expected thongSoCheck5 to be selected').to.be.true;
    }
    expect(await proSanPhamUpdatePage.getThongSoValue6Input()).to.eq(
      'thongSoValue6',
      'Expected ThongSoValue6 value to be equals to thongSoValue6'
    );
    expect(await proSanPhamUpdatePage.getThongSoCaption6Input()).to.eq(
      'thongSoCaption6',
      'Expected ThongSoCaption6 value to be equals to thongSoCaption6'
    );
    const selectedThongSoCheck6 = proSanPhamUpdatePage.getThongSoCheck6Input();
    if (await selectedThongSoCheck6.isSelected()) {
      await proSanPhamUpdatePage.getThongSoCheck6Input().click();
      expect(await proSanPhamUpdatePage.getThongSoCheck6Input().isSelected(), 'Expected thongSoCheck6 not to be selected').to.be.false;
    } else {
      await proSanPhamUpdatePage.getThongSoCheck6Input().click();
      expect(await proSanPhamUpdatePage.getThongSoCheck6Input().isSelected(), 'Expected thongSoCheck6 to be selected').to.be.true;
    }
    expect(await proSanPhamUpdatePage.getThongSoValue7Input()).to.eq(
      'thongSoValue7',
      'Expected ThongSoValue7 value to be equals to thongSoValue7'
    );
    expect(await proSanPhamUpdatePage.getThongSoCaption7Input()).to.eq(
      'thongSoCaption7',
      'Expected ThongSoCaption7 value to be equals to thongSoCaption7'
    );
    const selectedThongSoCheck7 = proSanPhamUpdatePage.getThongSoCheck7Input();
    if (await selectedThongSoCheck7.isSelected()) {
      await proSanPhamUpdatePage.getThongSoCheck7Input().click();
      expect(await proSanPhamUpdatePage.getThongSoCheck7Input().isSelected(), 'Expected thongSoCheck7 not to be selected').to.be.false;
    } else {
      await proSanPhamUpdatePage.getThongSoCheck7Input().click();
      expect(await proSanPhamUpdatePage.getThongSoCheck7Input().isSelected(), 'Expected thongSoCheck7 to be selected').to.be.true;
    }
    expect(await proSanPhamUpdatePage.getThongSoValue8Input()).to.eq(
      'thongSoValue8',
      'Expected ThongSoValue8 value to be equals to thongSoValue8'
    );
    expect(await proSanPhamUpdatePage.getThongSoCaption8Input()).to.eq(
      'thongSoCaption8',
      'Expected ThongSoCaption8 value to be equals to thongSoCaption8'
    );
    const selectedThongSoCheck8 = proSanPhamUpdatePage.getThongSoCheck8Input();
    if (await selectedThongSoCheck8.isSelected()) {
      await proSanPhamUpdatePage.getThongSoCheck8Input().click();
      expect(await proSanPhamUpdatePage.getThongSoCheck8Input().isSelected(), 'Expected thongSoCheck8 not to be selected').to.be.false;
    } else {
      await proSanPhamUpdatePage.getThongSoCheck8Input().click();
      expect(await proSanPhamUpdatePage.getThongSoCheck8Input().isSelected(), 'Expected thongSoCheck8 to be selected').to.be.true;
    }
    expect(await proSanPhamUpdatePage.getThongSoValue9Input()).to.eq(
      'thongSoValue9',
      'Expected ThongSoValue9 value to be equals to thongSoValue9'
    );
    expect(await proSanPhamUpdatePage.getThongSoCaption9Input()).to.eq(
      'thongSoCaption9',
      'Expected ThongSoCaption9 value to be equals to thongSoCaption9'
    );
    const selectedThongSoCheck9 = proSanPhamUpdatePage.getThongSoCheck9Input();
    if (await selectedThongSoCheck9.isSelected()) {
      await proSanPhamUpdatePage.getThongSoCheck9Input().click();
      expect(await proSanPhamUpdatePage.getThongSoCheck9Input().isSelected(), 'Expected thongSoCheck9 not to be selected').to.be.false;
    } else {
      await proSanPhamUpdatePage.getThongSoCheck9Input().click();
      expect(await proSanPhamUpdatePage.getThongSoCheck9Input().isSelected(), 'Expected thongSoCheck9 to be selected').to.be.true;
    }
    expect(await proSanPhamUpdatePage.getThongSoValue10Input()).to.eq(
      'thongSoValue10',
      'Expected ThongSoValue10 value to be equals to thongSoValue10'
    );
    expect(await proSanPhamUpdatePage.getThongSoCaption10Input()).to.eq(
      'thongSoCaption10',
      'Expected ThongSoCaption10 value to be equals to thongSoCaption10'
    );
    const selectedThongSoCheck10 = proSanPhamUpdatePage.getThongSoCheck10Input();
    if (await selectedThongSoCheck10.isSelected()) {
      await proSanPhamUpdatePage.getThongSoCheck10Input().click();
      expect(await proSanPhamUpdatePage.getThongSoCheck10Input().isSelected(), 'Expected thongSoCheck10 not to be selected').to.be.false;
    } else {
      await proSanPhamUpdatePage.getThongSoCheck10Input().click();
      expect(await proSanPhamUpdatePage.getThongSoCheck10Input().isSelected(), 'Expected thongSoCheck10 to be selected').to.be.true;
    }
    expect(await proSanPhamUpdatePage.getColorInput()).to.eq('color', 'Expected Color value to be equals to color');
    expect(await proSanPhamUpdatePage.getHinhMatTruocURLInput()).to.eq(
      'hinhMatTruocURL',
      'Expected HinhMatTruocURL value to be equals to hinhMatTruocURL'
    );
    expect(await proSanPhamUpdatePage.getHinhMatSauURLInput()).to.eq(
      'hinhMatSauURL',
      'Expected HinhMatSauURL value to be equals to hinhMatSauURL'
    );
    await proSanPhamUpdatePage.save();
    expect(await proSanPhamUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await proSanPhamComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ProSanPham', async () => {
    const nbButtonsBeforeDelete = await proSanPhamComponentsPage.countDeleteButtons();
    await proSanPhamComponentsPage.clickOnLastDeleteButton();

    proSanPhamDeleteDialog = new ProSanPhamDeleteDialog();
    expect(await proSanPhamDeleteDialog.getDialogTitle()).to.eq('ctApp.proSanPham.delete.question');
    await proSanPhamDeleteDialog.clickOnConfirmButton();

    expect(await proSanPhamComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
