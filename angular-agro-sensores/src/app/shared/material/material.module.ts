
import { NgModule } from '@angular/core'; // este é o recurso necessario para "transformar" esta classe num angular module: seu proposito é: centralizar recursos semelhantes; neste caso, estamos tentanto centralizar recursos com origem no material design

// import { CommonModule } from '@angular/common'; // este é um recurso que é necessario para utilizarmos alguns elementos que o angular core nos oferece!!! neste caso, aqui, ele não é necessario 

// agora, vamos importar os modulos do material 
import { MatToolbarModule } from '@angular/material/toolbar'; // auxilia na construção das barras superiores de componentes

import { MatButtonModule} from '@angular/material/button'; // auxilia na cosntrução buttons nas views
import { MatIconModule } from '@angular/material/icon'; // auxilia na construção de iconização da view
import { MatCardModule } from '@angular/material/card'; // auxilia na construçãod e cards
import { MatFormFieldModule } from '@angular/material/form-field'; // auxilia na construção de estruturas de formulario
import { MatInputModule } from '@angular/material/input'; // auxilia na construção de inputs e formulario
import { MatSnackBarModule } from '@angular/material/snack-bar'; // auxilia na construção de feedback visual de operações
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatMenuModule } from '@angular/material/menu';
import { MatListModule } from '@angular/material/list';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';


@NgModule({ // aqui esta o "decorator" que "transforma" esta classe num angular module
  /*declarations: [],
  imports: [
    //CommonModule
  ]*/
 exports: [
  MatToolbarModule,
  MatIconModule,
  MatButtonModule,
  MatCardModule,
  MatFormFieldModule,
  MatInputModule,
  MatSnackBarModule,
  MatProgressSpinnerModule,
  MatMenuModule,
  MatListModule,
  MatSelectModule,
  MatTableModule
 ]
})
export class MaterialModule { }
