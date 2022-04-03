/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto_fase2_202010223;

/**
 *
 * @author Alex Rose
 */
public class ArbolB {
    int orden_arbol=5;
    RamaB raiz;
    Cliente logueado;
    long id;
    String clave;
    String nodos;
    String estructura;
    ArbolB clon;
    
    public ArbolB(){
        this.raiz=null;
    }
    
    public void insertar(Cliente cliente){
        NodoB nodo=new NodoB(cliente);
        if(raiz==null){
            raiz=new RamaB();
            raiz.insertar(nodo);
        }else{
            NodoB obj=insertarEnRama(nodo,raiz);
            if(obj!=null){
                raiz=new RamaB();
                raiz.insertar(obj);
                raiz.hoja=false;
            }
        }
    }
    
    public NodoB insertarEnRama(NodoB nodo, RamaB rama){
        if(rama.hoja==true){
            rama.insertar(nodo);
            if(rama.contador==orden_arbol){
                return dividir(rama);
            }else{
                return null;
            }
        }else{
            NodoB temp=rama.primero;
            do{
                if(nodo.cliente.getDpi()==temp.cliente.getDpi()){
                    return null; //si encontro otro igual
                }else if(nodo.cliente.getDpi()<temp.cliente.getDpi()){
                    NodoB obj=insertarEnRama(nodo,temp.izquierda);
                    if(obj instanceof NodoB){
                        rama.insertar((NodoB) obj);
                        if(rama.contador==orden_arbol){
                            return dividir(rama);
                        }
                    }
                    return null;
                }else if(temp.siguiente==null){
                    NodoB obj=insertarEnRama(nodo,temp.derecha);
                    if(obj instanceof NodoB){
                        rama.insertar((NodoB) obj);
                        if(rama.contador==orden_arbol){
                            return dividir(rama);
                        }
                    }
                    return null;
                }
                temp=(NodoB) temp.siguiente;
            }while(temp!=null);
        }
        return null;
    }
    
    public NodoB dividir(RamaB rama){
        Cliente val=null;
        NodoB temp;
        NodoB nuevito;
        NodoB aux=rama.primero;
        RamaB rderecha=new RamaB();
        RamaB rizquierda=new RamaB();
        
        int contador=0;
        while(aux!=null){
            contador++;
            if(contador<3){
                temp=new NodoB(aux.cliente);
                temp.izquierda=aux.izquierda;
                if(contador==2){
                    temp.derecha=aux.siguiente.izquierda;
                }else{
                    temp.derecha=aux.derecha;
                }
                if(temp.derecha!=null&&temp.izquierda!=null){
                    rizquierda.hoja=false;
                }
                rizquierda.insertar(temp);
            }else if(contador==3){
                val=aux.cliente;
            }else{
                temp=new NodoB(aux.cliente);
                temp.izquierda=aux.izquierda;
                temp.derecha=aux.derecha;
                if(temp.derecha!=null&&temp.izquierda!=null){
                    rderecha.hoja=false;
                }
                rderecha.insertar(temp);
            }
            aux=aux.siguiente;
        }
        nuevito=new NodoB(val);
        nuevito.derecha=rderecha;
        nuevito.izquierda=rizquierda;
        return nuevito;
    }
    
    public void recorrer(RamaB temp){
        if(temp!=null){
            recorrer(temp.primero);
        }
    }
    
    public void recorrer(NodoB temp){
        NodoB actual=temp;
        while(actual!=null){
            //System.out.print(actual.cliente.getDpi()+" ");
            if(actual.cliente.getDpi()==id&&actual.cliente.getPassword().equals(clave)){
                logueado=actual.cliente;
            }
            actual=actual.siguiente;
        }
        System.out.println(" ");
        actual=temp;
        while(actual!=null){
            verificar(actual);
            actual=actual.siguiente;
        }
    }
    
    public void verificar(NodoB temp){
        if(temp!=null){
            if(temp.izquierda!=null){
                recorrer(temp.izquierda);
            }
            if(temp.siguiente==null){
                recorrer(temp.derecha);
            }
        }
    }
    
    public Cliente logueado(long dpi,String contra){
        id=dpi;
        clave=contra;
        logueado=null;
        recorrer(raiz);
        return logueado;
    }
    
    
    
    public String graficar(){
        String dot = "digraph G {\n"
                + "\n"
                + "node[shape=record]\n";
        nodos = "";
        estructura="";
        recorrer2(raiz);
        
        dot+=nodos+estructura+"\n}";
        //System.out.println(dot);
        return dot;
    }
    
    public void recorrer2(RamaB temp) {
        if (temp != null) {
            recorrer2(temp.primero,temp);
        }
    }

    public void recorrer2(NodoB temp,RamaB rama) {
        NodoB actual = temp;
        int iterador=0;
        nodos+="Rama"+rama.hashCode()+"[label=\"";
        while (actual != null) {
            if(actual.izquierda!=null){
                nodos+="<I"+iterador+">";
                estructura+="Rama"+rama.hashCode()+":I"+iterador+"->"+"Rama"+actual.izquierda.hashCode()+";\n";
                iterador++;
            }
            //System.out.print(actual.id + " ");
            nodos+="|"+actual.cliente.getDpi()+"|";
            
            if(actual.siguiente==null&&actual.derecha!=null){
                nodos+="<D"+iterador+">";
                estructura+="Rama"+rama.hashCode()+":D"+iterador+"->"+"Rama"+actual.derecha.hashCode()+";\n";
                iterador++;
            }
            actual = actual.siguiente;
            
        }
        nodos+="\"];\n";
        System.out.println(" ");
        actual = temp;
        while (actual != null) {
            verificar2(actual);
            actual = actual.siguiente;
        }

    }

    public void verificar2(NodoB temp) {
        if (temp != null) {
            if (temp.izquierda != null) {
                //System.out.println("\nverificando izquierda de "+temp.id);
                recorrer2(temp.izquierda);
            }
            if (temp.siguiente == null) {
                //System.out.println("\nverificando derecha de "+temp.id);
                recorrer2(temp.derecha);
            }
        }
    }
    
    public void recorrer3(RamaB temp,Administrador a){
        if(temp!=null){
            recorrer3(temp.primero,a);
        }
    }
    
    public void recorrer3(NodoB temp,Administrador a){
        NodoB actual=temp;
        while(actual!=null){
            a.Comboclientes.addItem(String.valueOf(actual.cliente.getDpi()));
            actual=actual.siguiente;
        }
        System.out.println(" ");
        actual=temp;
        while(actual!=null){
            verificar3(actual,a);
            actual=actual.siguiente;
        }
    }
    
    public void verificar3(NodoB temp,Administrador a){
        if(temp!=null){
            if(temp.izquierda!=null){
                recorrer3(temp.izquierda,a);
            }
            if(temp.siguiente==null){
                recorrer3(temp.derecha,a);
            }
        }
    }
    
    public Cliente buscarcliente(long dpi){
        id=dpi;
        logueado=null;
        recorrer4(raiz);
        return logueado;
    }
    
    public void recorrer4(RamaB temp){
        if(temp!=null){
            recorrer4(temp.primero);
        }
    }
    
    public void recorrer4(NodoB temp){
        NodoB actual=temp;
        while(actual!=null){
            //System.out.print(actual.cliente.getDpi()+" ");
            if(actual.cliente.getDpi()==id){
                logueado=actual.cliente;
            }
            actual=actual.siguiente;
        }
        System.out.println(" ");
        actual=temp;
        while(actual!=null){
            verificar4(actual);
            actual=actual.siguiente;
        }
    }
    
    public void verificar4(NodoB temp){
        if(temp!=null){
            if(temp.izquierda!=null){
                recorrer4(temp.izquierda);
            }
            if(temp.siguiente==null){
                recorrer4(temp.derecha);
            }
        }
    }
    
    
    public void eliminar(long dpi){
        clon=new ArbolB();
        id=dpi;
        recorrer5(raiz);
        raiz=clon.raiz;
    }
    
    public void recorrer5(RamaB temp){
        if(temp!=null){
            recorrer5(temp.primero);
        }
    }
    
    public void recorrer5(NodoB temp){
        NodoB actual=temp;
        while(actual!=null){
            //System.out.print(actual.cliente.getDpi()+" ");
            if(actual.cliente.getDpi()!=id){
                clon.insertar(actual.cliente);
            }
            actual=actual.siguiente;
        }
        System.out.println(" ");
        actual=temp;
        while(actual!=null){
            verificar5(actual);
            actual=actual.siguiente;
        }
    }
    
    public void verificar5(NodoB temp){
        if(temp!=null){
            if(temp.izquierda!=null){
                recorrer5(temp.izquierda);
            }
            if(temp.siguiente==null){
                recorrer5(temp.derecha);
            }
        }
    }
    
    public void actualizarniveles(){
        int iterador=0;
        recorrer6(raiz,iterador);
    }
    
    public void recorrer6(RamaB temp,int c) {
        if (temp != null) {
            temp.nivel=c;
            recorrer6(temp.primero,temp,c);
        }
    }

    public void recorrer6(NodoB temp,RamaB rama,int c) {
        NodoB actual = temp;
        System.out.print("");
        while (actual != null) {
            System.out.print(actual.cliente.getDpi()+ " ");
            actual = actual.siguiente;
        }
        System.out.println(" ");
        actual = temp;
        while (actual != null) {
            verificar6(actual,c);
            actual = actual.siguiente;
        }

    }

    public void verificar6(NodoB temp,int c) {
        if (temp != null) {
            if (temp.izquierda != null) {
                //System.out.println("\nverificando izquierda de "+temp.id);
                recorrer6(temp.izquierda,c+1);
            }
            if (temp.siguiente == null) {
                //System.out.println("\nverificando derecha de "+temp.id);
                recorrer6(temp.derecha,c+1);
            }
        }
    }
    
    public String tablaniveles(){
        estructura="";
        recorrer7(raiz);
        return estructura;
    }
    
    public void recorrer7(RamaB temp) {
        if (temp != null) {
            recorrer7(temp.primero,temp.nivel);
        }
    }

    public void recorrer7(NodoB temp, int nivel) {
        NodoB actual = temp;
        while (actual != null) {
            estructura+="<tr>\n";
            estructura+="<td>"+actual.cliente.getNombre()+"</td>\n";
            estructura+="<td>"+actual.cliente.getDpi()+"</td>\n";
            estructura+="<td>"+nivel+"</td>\n";
            estructura+="<td>"+actual.cliente.imagenes.totalimagenes()+"</td>\n";
            estructura+="</tr>\n";
            System.out.print(actual.cliente.getDpi() + " ");
            actual = actual.siguiente;
        }
        System.out.println(" ");
        actual = temp;
        while (actual != null) {
            verificar7(actual);
            actual = actual.siguiente;
        }

    }

    public void verificar7(NodoB temp) {
        if (temp != null) {
            if (temp.izquierda != null) {
                //System.out.println("\nverificando izquierda de "+temp.id);
                recorrer7(temp.izquierda);
            }
            if (temp.siguiente == null) {
                //System.out.println("\nverificando derecha de "+temp.id);
                recorrer7(temp.derecha);
            }
        }
    }
    
}
