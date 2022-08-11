package edu.bu.cs665.person;

import edu.bu.cs665.AbstractEntity;

import java.util.List;

public abstract class Title extends AbstractEntity {
    List<TitleResponsibility<?>> responsibilities;
}
