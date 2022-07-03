package excersises;

import java.util.*;

public class JavaClasses 
{
    public class OrderItem
    {
        private String name;
        private Integer quantity;
        private Double price;

        {
            name="";
            quantity=0;
            price=0.0;
        }

        public OrderItem(){}

        public String getName(){return name;}
        public Integer getQuantity(){return quantity;}
        public Double getPrice(){return price;}

        public void setName(String _name){name=_name;}
        public void setQuantity(Integer _quantity){quantity=_quantity;}
        public void setPrice(Double _price){price=_price;}

        public OrderItem(String _name, Integer _quantity, Double _price)
        {
            name = _name;
            quantity = _quantity;
            price = _price;
        }

        public Double countPrice()
        {
            return quantity*price;
        }

        public String toString()
        {
            return name+"  Price["+price+"]  Quantity["+quantity+"]  Total Cost["+countPrice()+"]";
        }
    }

    public class Order
    {
        private List<OrderItem> items;

        {
            items = new ArrayList<>();
        }

        public Order(){}
        public Order(List<OrderItem> _items)
        {
            items=_items;
        }

        public void addItem(OrderItem item)
        {
            for (OrderItem orderItem : items) {
                if(orderItem.getName()==item.getName())
                {
                    orderItem.setQuantity(orderItem.getQuantity()+item.getQuantity());
                    return;
                }
            }
           
            items.add(item);
        }

        public Double countDiscount()
        {
            int size = items.size();
            
            if(size<5)
                return 0.0;
            else if(size>=5 && size<=10)
                return 0.05;
            else if(size>10&&size<=20)
                return 0.1;
            else
                return 0.15;
            
        }

        public Double countPrice()
        { 
            Double price = 0.0;

            for (OrderItem orderItem : items) {
                price+=orderItem.countPrice();
            }
            return price;
        }

        public Double countPriceWithDiscount()
        {
            return countPrice()*(1-countDiscount());
        }

        public String toString()
        {
            StringBuilder sb = new StringBuilder();

            for (OrderItem orderItem : items) {
                sb.append(orderItem.toString()+"\n");
            }
            sb.append("\n\nTotal price["+ countPrice()+"]  Discount[" + countDiscount()*100+"%]"+ "  Price with discount["+countPriceWithDiscount()+"]\n");

            return sb.toString();
        }

        public void removeItem(int index) throws ArrayIndexOutOfBoundsException
        {
            items.remove(index);
        }

        public void editItem(int index, OrderItem newItem) throws ArrayIndexOutOfBoundsException
        {
            items.set(index, newItem);
        }
    }
}
