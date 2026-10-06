import { Injectable } from '@angular/core';

export class Menu {
  id: string;
  name: string;
  icon?: string;
  link?: string;
}

const menuItems: Menu[] = [
  {
    id: '1',
    name: 'Line 1',
    icon: 'chart-bar',
    link: 'line-1'
  },
  {
    id: '2',
    name: 'Line 2',
    icon: 'chart-bar'
  },
  {
    id: '3',
    name: 'Line 3',
    icon: 'chart-bar'
  },
  {
    id: '4',
    name: 'Line 4',
    icon: 'chart-bar'
  },
  {
    id: '5',
    name: 'Line 5',
    icon: 'chart-bar'
  },
  {
    id: '6',
    name: 'Line 6',
    icon: 'chart-bar'
  },
  {
    id: '7',
    name: 'Line 7',
    icon: 'chart-bar'
  },
  {
    id: '8',
    name: 'Line 8',
    icon: 'chart-bar'
  }
];
@Injectable({
  providedIn: 'root'
})
export class ServiceService {
  constructor() {}
  getMenuItems(): Menu[] {
    return menuItems;
  }
}
