import { Component } from '@angular/core';

@Component({
  selector: 'app-precos',
  templateUrl: './precos.component.html',
  styleUrls: ['./precos.component.css']
})
export class PrecosComponent {
  plans = [
    {
      price: 'Free',
      items: [
        { name: 'item 1', available: true },
        { name: 'item 2', available: true },
        // Outros itens
      ]
    },
    {
      price: 'R$ 200,00',
      items: [
        { name: 'item 1', available: true },
        { name: 'item 2', available: false },
        // Outros itens
      ]
    },
    {
      price: 'R$ 450,00',
      items: [
        { name: 'item 1', available: true },
        { name: 'item 2', available: true },
        // Outros itens
      ]
    }
  ];
}
